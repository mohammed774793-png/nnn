package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.PharmaViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StudyCenterScreen(viewModel: PharmaViewModel) {
    var selectedSuiteTab by remember { mutableStateOf(0) } // 0 = Quiz, 1 = Bookmarks, 2 = Saved Notes, 3 = History

    val bookmarks by viewModel.bookmarks.collectAsState()
    val studyNotes by viewModel.studyNotes.collectAsState()
    val quizRecords by viewModel.quizRecords.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Suite Title
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.School, contentDescription = "Study Hub", tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Interactive Study Suite",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = "Assess, bookmark, and review pharmacology concepts with local storage.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Grid/Toggles for Suite tabs
        ScrollableTabRow(
            selectedTabIndex = selectedSuiteTab,
            containerColor = Color.Transparent,
            edgePadding = 0.dp
        ) {
            Tab(
                selected = selectedSuiteTab == 0,
                onClick = { selectedSuiteTab = 0 },
                text = { Text("Self-Quiz") }
            )
            Tab(
                selected = selectedSuiteTab == 1,
                onClick = { selectedSuiteTab = 1 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Bookmarks")
                        if (bookmarks.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Badge { Text(bookmarks.size.toString()) }
                        }
                    }
                }
            )
            Tab(
                selected = selectedSuiteTab == 2,
                onClick = { selectedSuiteTab = 2 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("My Notes")
                        if (studyNotes.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Badge { Text(studyNotes.size.toString()) }
                        }
                    }
                }
            )
            Tab(
                selected = selectedSuiteTab == 3,
                onClick = { selectedSuiteTab = 3 },
                text = { Text("Records") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Render Active Suite Panel
        Box(modifier = Modifier.weight(1f)) {
            when (selectedSuiteTab) {
                0 -> ActiveQuizModule(viewModel)
                1 -> BookmarksModule(viewModel)
                2 -> SavedNotesModule(viewModel)
                3 -> HistoryModule(viewModel)
            }
        }
    }
}

@Composable
fun ActiveQuizModule(viewModel: PharmaViewModel) {
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val selectedIndex by viewModel.selectedAnswerIndex.collectAsState()
    val score by viewModel.quizScore.collectAsState()
    val isFinished by viewModel.isQuizFinished.collectAsState()
    val showExplanation by viewModel.showExplanation.collectAsState()

    val questions = viewModel.quizQuestions

    if (isFinished) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.MilitaryTech,
                    contentDescription = "Success Trophy",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Quiz Completed!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Pharmacology Lecture: Antihyperlipidemic Drugs",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Score Banner
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Your Score", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = score.toString(),
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = " / ${questions.size}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                    val percentage = (score * 100f / questions.size).toInt()
                    Text(
                        text = "Accuracy: $percentage% - ${if (percentage >= 80) "Excellent Clinical Acumen!" else "Keep studying to solidify drug facts!"}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.resetQuiz() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Retry")
                Spacer(modifier = Modifier.width(6.dp))
                Text("Restart Self-Quiz", fontWeight = FontWeight.Bold)
            }
        }
    } else {
        val q = questions[currentIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Quiz progress indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Question ${currentIndex + 1} of ${questions.size}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Score: $score",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = (currentIndex + 1) * 1.0f / questions.size,
                modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Question Card
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = q.question,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(16.dp),
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Options List
            q.options.forEachIndexed { index, optionText ->
                val isSelected = selectedIndex == index
                val borderCol = if (showExplanation) {
                    if (index == q.correctIndex) Color(0xFF2E7D32)
                    else if (isSelected) Color(0xFFC62828)
                    else MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                } else {
                    if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                }
                
                val containerCol = if (showExplanation) {
                    if (index == q.correctIndex) Color(0xFFE8F5E9)
                    else if (isSelected) Color(0xFFFFEBEE)
                    else MaterialTheme.colorScheme.surface
                } else {
                    if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
                    else MaterialTheme.colorScheme.surface
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable(enabled = !showExplanation) { viewModel.selectAnswer(index) },
                    colors = CardDefaults.cardColors(containerColor = containerCol),
                    border = BorderStroke(if (isSelected || (showExplanation && index == q.correctIndex)) 2.dp else 1.dp, borderCol),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Custom Index bullet
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(
                                    if (showExplanation && index == q.correctIndex) Color(0xFF2E7D32)
                                    else if (showExplanation && isSelected) Color(0xFFC62828)
                                    else if (isSelected) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ('A' + index).toString(),
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected || (showExplanation && (index == q.correctIndex || isSelected))) Color.White
                                    else MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )

                        if (showExplanation) {
                            if (index == q.correctIndex) {
                                Icon(Icons.Default.Check, contentDescription = "Correct", tint = Color(0xFF2E7D32))
                            } else if (isSelected) {
                                Icon(Icons.Default.Close, contentDescription = "Incorrect", tint = Color(0xFFC62828))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action row
            if (!showExplanation) {
                Button(
                    onClick = { viewModel.submitAnswer() },
                    enabled = selectedIndex != null,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Submit Answer", fontWeight = FontWeight.Bold)
                }
            } else {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.15f)),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Assignment, contentDescription = "Rationale", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Medical Rationale & Feedback", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = q.explanation,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 15.sp
                        )
                    }
                }

                Button(
                    onClick = { viewModel.nextQuestion() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (currentIndex < questions.size - 1) "Next Question" else "Finish and Log Score", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(Icons.Default.ArrowForward, contentDescription = "Proceed")
                }
            }
        }
    }
}

@Composable
fun BookmarksModule(viewModel: PharmaViewModel) {
    val bookmarks by viewModel.bookmarks.collectAsState()

    if (bookmarks.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
                Icon(Icons.Default.BookmarkBorder, contentDescription = "No bookmarks", tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(48.dp))
                Spacer(modifier = Modifier.height(12.dp))
                Text("No Bookmarks Yet", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                Text(
                    text = "Go to the 'Drugs Directory' tab and click the bookmark icon to save high-yield drug classes here.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(bookmarks.size, key = { bookmarks[it].topicId }) { index ->
                val b = bookmarks[index]
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.Label, contentDescription = "Bookmark", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(b.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                                Text(
                                    text = "Bookmarked on: ${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(b.bookmarkedAt))}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }

                        IconButton(
                            onClick = { viewModel.toggleBookmark(b.topicId, b.title, true) }
                        ) {
                            Icon(Icons.Default.Bookmark, contentDescription = "Remove Bookmark", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SavedNotesModule(viewModel: PharmaViewModel) {
    val studyNotes by viewModel.studyNotes.collectAsState()

    if (studyNotes.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
                Icon(Icons.Default.NoteAlt, contentDescription = "No notes", tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(48.dp))
                Spacer(modifier = Modifier.height(12.dp))
                Text("No Notes Written", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                Text(
                    text = "You can add custom study mnemonics, equations or facts at the bottom of any drug category under the 'Drugs Directory' tab.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(studyNotes.size, key = { studyNotes[it].topicId }) { index ->
                val note = studyNotes[index]
                val formattedTopic = when (note.topicId) {
                    "statins" -> "HMG-CoA Reductase Inhibitors (Statins)"
                    "resins" -> "Bile Acid Sequestrants (Resins)"
                    "fibrates" -> "Fibrates (Lipoprotein Lipase Activators)"
                    "niacin" -> "Lipolysis Inhibitors (Niacin / Nicotinic Acid)"
                    "ezetimibe" -> "Sterol Absorption Inhibitors"
                    else -> note.topicId
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = formattedTopic,
                                fontWeight = FontWeight.ExtraBold,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconButton(
                                onClick = { viewModel.deleteNoteDirectly(note.topicId) },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete Note", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(16.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = note.content,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 15.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Last updated: ${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(note.updatedAt))}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryModule(viewModel: PharmaViewModel) {
    val quizRecords by viewModel.quizRecords.collectAsState()

    if (quizRecords.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
                Icon(Icons.Default.History, contentDescription = "No records", tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(48.dp))
                Spacer(modifier = Modifier.height(12.dp))
                Text("No Quiz Records Yet", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                Text(
                    text = "Complete the Self-Quiz assessment to log your scores here, tracking your progress.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            // Summary Stat card
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val averageScore = remember(quizRecords) {
                        quizRecords.map { it.score * 100f / it.totalQuestions }.average().toInt()
                    }
                    Column {
                        Text("Performance Summary", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                        Text("Average Accuracy: $averageScore%", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("Total Quizzes Taken: ${quizRecords.size}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                    }
                    
                    TextButton(onClick = { viewModel.clearQuizRecords() }) {
                        Icon(Icons.Default.ClearAll, contentDescription = "Clear")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Clear All")
                    }
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(quizRecords.size, key = { quizRecords[it].id }) { index ->
                    val r = quizRecords[index]
                    val percentage = (r.score * 100f / r.totalQuestions).toInt()
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .background(
                                            if (percentage >= 80) Color(0xFFE8F5E9) else Color(0xFFFFF3E0),
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (percentage >= 80) Icons.Default.CheckCircle else Icons.Default.Info,
                                        contentDescription = "Success",
                                        tint = if (percentage >= 80) Color(0xFF2E7D32) else Color(0xFFE65100)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text("Score: ${r.score} / ${r.totalQuestions} ($percentage%)", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                                    Text(
                                        text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(r.timestamp)),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
