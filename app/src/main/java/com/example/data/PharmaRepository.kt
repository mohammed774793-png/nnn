package com.example.data

import kotlinx.coroutines.flow.Flow

class PharmaRepository(private val pharmaDao: PharmaDao) {
    val allNotes: Flow<List<StudyNote>> = pharmaDao.getAllNotes()
    val allBookmarks: Flow<List<Bookmark>> = pharmaDao.getAllBookmarks()
    val allQuizRecords: Flow<List<QuizRecord>> = pharmaDao.getAllQuizRecords()

    suspend fun getNoteById(topicId: String): StudyNote? = pharmaDao.getNoteById(topicId)

    suspend fun saveNote(topicId: String, content: String) {
        pharmaDao.insertNote(StudyNote(topicId = topicId, content = content))
    }

    suspend fun deleteNote(topicId: String) {
        val note = pharmaDao.getNoteById(topicId)
        if (note != null) {
            pharmaDao.deleteNote(note)
        }
    }

    suspend fun toggleBookmark(topicId: String, title: String, isBookmarked: Boolean) {
        if (isBookmarked) {
            pharmaDao.insertBookmark(Bookmark(topicId = topicId, title = title))
        } else {
            pharmaDao.deleteBookmarkById(topicId)
        }
    }

    fun isBookmarked(topicId: String): Flow<Boolean> = pharmaDao.isBookmarked(topicId)

    suspend fun saveQuizRecord(score: Int, total: Int) {
        pharmaDao.insertQuizRecord(QuizRecord(score = score, totalQuestions = total))
    }

    suspend fun clearQuizRecords() {
        pharmaDao.clearQuizRecords()
    }
}
