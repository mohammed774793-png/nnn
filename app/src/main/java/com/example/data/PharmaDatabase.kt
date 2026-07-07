package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "study_notes")
data class StudyNote(
    @PrimaryKey val topicId: String,
    val content: String,
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "bookmarks")
data class Bookmark(
    @PrimaryKey val topicId: String,
    val title: String,
    val bookmarkedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_records")
data class QuizRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val score: Int,
    val totalQuestions: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Dao
interface PharmaDao {
    // Study Notes
    @Query("SELECT * FROM study_notes")
    fun getAllNotes(): Flow<List<StudyNote>>

    @Query("SELECT * FROM study_notes WHERE topicId = :topicId LIMIT 1")
    suspend fun getNoteById(topicId: String): StudyNote?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: StudyNote)

    @Delete
    suspend fun deleteNote(note: StudyNote)

    // Bookmarks
    @Query("SELECT * FROM bookmarks ORDER BY bookmarkedAt DESC")
    fun getAllBookmarks(): Flow<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmarks WHERE topicId = :topicId")
    suspend fun deleteBookmarkById(topicId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE topicId = :topicId)")
    fun isBookmarked(topicId: String): Flow<Boolean>

    // Quiz Records
    @Query("SELECT * FROM quiz_records ORDER BY timestamp DESC")
    fun getAllQuizRecords(): Flow<List<QuizRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizRecord(record: QuizRecord)

    @Query("DELETE FROM quiz_records")
    suspend fun clearQuizRecords()
}

@Database(entities = [StudyNote::class, Bookmark::class, QuizRecord::class], version = 1, exportSchema = false)
abstract class PharmaDatabase : RoomDatabase() {
    abstract fun pharmaDao(): PharmaDao
}
