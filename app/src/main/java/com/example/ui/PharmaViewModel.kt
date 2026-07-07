package com.example.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

class PharmaViewModel(private val repository: PharmaRepository) : ViewModel() {

    // Bottom Navigation Tab state
    private val _currentTab = MutableStateFlow("learn")
    val currentTab: StateFlow<String> = _currentTab.asStateFlow()

    fun selectTab(tab: String) {
        _currentTab.value = tab
    }

    // Room Database states
    val studyNotes: StateFlow<List<StudyNote>> = repository.allNotes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarks: StateFlow<List<Bookmark>> = repository.allBookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val quizRecords: StateFlow<List<QuizRecord>> = repository.allQuizRecords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Search and filters for Drugs
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedClassFilter = MutableStateFlow<String?>(null)
    val selectedClassFilter: StateFlow<String?> = _selectedClassFilter.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectClassFilter(filter: String?) {
        _selectedClassFilter.value = filter
    }

    // Quiz State
    val quizQuestions = listOf(
        QuizQuestion(
            question = "Which enzyme is directly inhibited by Statins?",
            options = listOf("Lipoprotein Lipase", "HMG-CoA Reductase", "Pancreatic Lipase", "Acyl-CoA transferase"),
            correctIndex = 1,
            explanation = "Statins directly block HMG-CoA reductase, the rate-limiting enzyme that converts Acetate/HMG-CoA into Mevalonic Acid (the precursor of cholesterol) inside hepatocytes."
        ),
        QuizQuestion(
            question = "Why are Atorvastatin and Rosuvastatin exceptions to the 'take at night' rule?",
            options = listOf("They are not metabolized by the liver", "They have a very long half-life (duration of action)", "They do not cause myopathy", "They work locally in the gastrointestinal tract"),
            correctIndex = 1,
            explanation = "While most statins must be taken at night to align with peak endogenous cholesterol synthesis, Atorvastatin and Rosuvastatin have a long half-life and provide equivalent efficacy regardless of administration timing."
        ),
        QuizQuestion(
            question = "Which statin is renal excretion prominent for, requiring dosage adjustments in renal failure?",
            options = listOf("Atorvastatin", "Rosuvastatin", "Pravastatin", "Simvastatin"),
            correctIndex = 2,
            explanation = "Most statins are primarily excreted in the bile and are safe for renal impairment. Pravastatin is the exception, being cleared renally, and requires careful dosing in renal failure."
        ),
        QuizQuestion(
            question = "A patient taking Bile Acid Resins presents with bleeding. What is the most likely pharmacological explanation?",
            options = listOf("Direct inhibition of platelets by the drug", "Impaired absorption of fat-soluble Vitamin K, leading to clotting factor deficiency", "Underlying severe liver rhabdomyolysis", "A direct chemical interaction with low-dose aspirin"),
            correctIndex = 1,
            explanation = "Resins are not absorbed but bind bile in the GI tract. Depletion of bile impairs absorption of fat-soluble vitamins (A, D, E, K). Vitamin K deficiency directly inhibits coagulation factor synthesis, carrying a bleeding risk."
        ),
        QuizQuestion(
            question = "Which antihyperlipidemic combination is absolutely contraindicated due to severe rhabdomyolysis/myopathy risk?",
            options = listOf("Atorvastatin + Ezetimibe", "Rosuvastatin + Niacin", "Simvastatin + Gemfibrozil", "Fenofibrate + Ezetimibe"),
            correctIndex = 2,
            explanation = "Combining Statins with Gemfibrozil (a first-generation fibrate) is strictly contraindicated. It severely impairs statin glucuronidation, raising blood levels and triggering life-threatening rhabdomyolysis."
        ),
        QuizQuestion(
            question = "How do Second-Generation Fibrates (like Fenofibrate) differ from First-Generation Fibrates?",
            options = listOf("They work locally in the gut instead of the liver", "They act as PPAR-alpha gene activators, significantly increasing good HDL levels", "They primarily target cholesterol instead of triglycerides", "They do not carry any risks of gallstones"),
            correctIndex = 1,
            explanation = "Second-generation fibrates (Fenofibrate, Bezafibrate) not only activate Lipoprotein Lipase to degrade triglycerides but also activate PPAR-alpha receptors, which actively enhances the expression of protective HDL."
        ),
        QuizQuestion(
            question = "Which drug class is the most potent for raising HDL levels, but causes cutaneous flushing and postural hypotension?",
            options = listOf("Ezetimibe", "Niacin / Nicotinic Acid", "Bile Acid Resins", "HMG-CoA Reductase Inhibitors"),
            correctIndex = 1,
            explanation = "Niacin is the most potent drug class for raising HDL levels. It causes cutaneous vasodilation (flushing) and postural hypotension via prostaglandin release, which can be mitigated by taking it with meals."
        ),
        QuizQuestion(
            question = "How does Ezetimibe differ from Bile Acid Sequestrants (Resins)?",
            options = listOf("It works systemically in the blood", "It targets triglyceride degradation", "It directly binds to dietary/biliary cholesterol instead of bile acids", "It does not carry a risk of fat-soluble vitamin deficiency"),
            correctIndex = 2,
            explanation = "Ezetimibe binds directly to cholesterol in the GI brush border to inhibit its absorption. Resins bind bile acids. Both can impair fat-soluble vitamin absorption, but Ezetimibe causes fewer GI side effects."
        )
    )

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    private val _quizScore = MutableStateFlow(0)
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    private val _showExplanation = MutableStateFlow(false)
    val showExplanation: StateFlow<Boolean> = _showExplanation.asStateFlow()

    fun selectAnswer(index: Int) {
        if (_showExplanation.value) return // prevent changing answer after submission
        _selectedAnswerIndex.value = index
    }

    fun submitAnswer() {
        val selected = _selectedAnswerIndex.value ?: return
        val currentQ = quizQuestions[_currentQuestionIndex.value]
        if (selected == currentQ.correctIndex) {
            _quizScore.value += 1
        }
        _showExplanation.value = true
    }

    fun nextQuestion() {
        _selectedAnswerIndex.value = null
        _showExplanation.value = false
        val nextIndex = _currentQuestionIndex.value + 1
        if (nextIndex < quizQuestions.size) {
            _currentQuestionIndex.value = nextIndex
        } else {
            _isQuizFinished.value = true
            // Save quiz record to database
            viewModelScope.launch {
                repository.saveQuizRecord(_quizScore.value, quizQuestions.size)
            }
        }
    }

    fun resetQuiz() {
        _currentQuestionIndex.value = 0
        _selectedAnswerIndex.value = null
        _quizScore.value = 0
        _isQuizFinished.value = false
        _showExplanation.value = false
    }

    fun clearQuizRecords() {
        viewModelScope.launch {
            repository.clearQuizRecords()
        }
    }

    // Study note editor state
    private val _activeNoteTopic = MutableStateFlow<String?>(null)
    val activeNoteTopic: StateFlow<String?> = _activeNoteTopic.asStateFlow()

    private val _activeNoteText = MutableStateFlow("")
    val activeNoteText: StateFlow<String> = _activeNoteText.asStateFlow()

    fun openNoteEditor(topicId: String) {
        _activeNoteTopic.value = topicId
        viewModelScope.launch {
            val note = repository.getNoteById(topicId)
            _activeNoteText.value = note?.content ?: ""
        }
    }

    fun updateNoteText(text: String) {
        _activeNoteText.value = text
    }

    fun saveActiveNote() {
        val topic = _activeNoteTopic.value ?: return
        viewModelScope.launch {
            if (_activeNoteText.value.isBlank()) {
                repository.deleteNote(topic)
            } else {
                repository.saveNote(topic, _activeNoteText.value)
            }
            _activeNoteTopic.value = null
            _activeNoteText.value = ""
        }
    }

    fun closeNoteEditor() {
        _activeNoteTopic.value = null
        _activeNoteText.value = ""
    }

    fun deleteNoteDirectly(topicId: String) {
        viewModelScope.launch {
            repository.deleteNote(topicId)
        }
    }

    // Bookmarking toggle
    fun toggleBookmark(topicId: String, title: String, currentBookmarked: Boolean) {
        viewModelScope.launch {
            repository.toggleBookmark(topicId, title, !currentBookmarked)
        }
    }

    fun isBookmarkedFlow(topicId: String): Flow<Boolean> = repository.isBookmarked(topicId)

    // Clinical Case State
    // Steps: 0 = Case Description, 1 = Discussion Options, 2 = Doctor Verdict / Explanation
    private val _caseStep = MutableStateFlow(0)
    val caseStep: StateFlow<Int> = _caseStep.asStateFlow()

    private val _selectedTreatmentOption = MutableStateFlow<String?>(null)
    val selectedTreatmentOption: StateFlow<String?> = _selectedTreatmentOption.asStateFlow()

    fun selectTreatmentOption(option: String) {
        _selectedTreatmentOption.value = option
    }

    fun submitTreatmentOption() {
        if (_selectedTreatmentOption.value != null) {
            _caseStep.value = 2 // Move to doctor verdict
        }
    }

    fun resetCase() {
        _caseStep.value = 0
        _selectedTreatmentOption.value = null
    }

    fun nextCaseStep() {
        if (_caseStep.value < 2) {
            _caseStep.value += 1
        }
    }
}

class PharmaViewModelFactory(private val repository: PharmaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PharmaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PharmaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
