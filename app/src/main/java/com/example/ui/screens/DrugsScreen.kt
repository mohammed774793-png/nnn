package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.PharmaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugsScreen(viewModel: PharmaViewModel) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filterClass by viewModel.selectedClassFilter.collectAsState()
    val studyNotes by viewModel.studyNotes.collectAsState()
    
    val activeNoteTopic by viewModel.activeNoteTopic.collectAsState()
    val activeNoteText by viewModel.activeNoteText.collectAsState()

    val drugClasses = remember {
        listOf(
            DrugClassData(
                id = "statins",
                name = "HMG-CoA Reductase Inhibitors (Statins)",
                subtitle = "First-Line / Drug of Choice for Hypercholesterolemia",
                indication = "Hypercholesterolemia (Elevated LDL & Cholesterol)",
                examples = listOf("Atorvastatin", "Rosuvastatin", "Simvastatin", "Pravastatin", "Lovastatin"),
                moaSteps = listOf(
                    "Direct Action: Reversibly inhibits HMG-CoA Reductase, stopping the cell from converting Acetate to HMG-CoA and then to Mevalonic Acid (the precursor of endogenous cholesterol).",
                    "Indirect Action: The liver cell responds to the severe depletion of internal cholesterol by upregulating and expressing more LDL Receptors on its cellular surface.",
                    "Systemic Effect: These newly expressed receptors actively capture circulating LDL and cholesterol from the blood, drastically lowering systemic cholesterol levels."
                ),
                kinetics = "Absorption: Good oral bioavailability.\nTiming: Usually taken AT NIGHT to align with peak endogenous cholesterol synthesis. Exceptions: Atorvastatin & Rosuvastatin have very long half-lives and can be taken at any time of day.\nMetabolism: Predominantly metabolized by Cytochrome CYP3A4, EXCEPT Rosuvastatin which utilizes Cytochrome CYP2C9 (minimizes key drug interactions).\nExcretion: Primarily excreted in Bile (safest for renal failure patients, except Pravastatin which is renally excreted).",
                adverseEffects = "Myopathy (muscle pain & weakness). Risk increases severely if combined with other lipid-lowering agents.",
                clinicalWarnings = listOf(
                    "ABSOLUTE CONTRAINDICATION: Never combine any Statin with Gemfibrozil (Fibrate) due to an extreme, lethal risk of rhabdomyolysis / muscle necrosis.",
                    "Pravastatin requires strict dosage adjustments in renal failure, unlike bile-excreted Statins."
                )
            ),
            DrugClassData(
                id = "resins",
                name = "Bile Acid Sequestrants (Resins)",
                subtitle = "Locally Acting Non-Absorbed Gut Bindings",
                indication = "Hypercholesterolemia (e.g. Type IIa / IIb)",
                examples = listOf("Cholestyramine", "Colestipol", "Colesevelam"),
                moaSteps = listOf(
                    "Direct Action: Resins are not absorbed systemically. They work locally in the gut lumen by binding to bile acids, forming an insoluble complex.",
                    "Fecal Excretion: The bound bile-acid-resin complex cannot be reabsorbed and is excreted directly in the feces.",
                    "Indirect Action: The body's bile pool is depleted. The liver must synthesize new bile. Because Cholesterol is the primary raw material for bile, the liver depletes its internal cholesterol stores.",
                    "Systemic Effect: Depleted hepatocytes upregulate surface LDL Receptors to pull cholesterol out of blood, thereby lowering circulating LDL."
                ),
                kinetics = "Absorption: ZERO systemic absorption. Action is entirely local.\nDose Profile: Taken in large gram amounts (3–15g) to physically capture big quantities of bile.",
                adverseEffects = "Severe GI distress (flatulence, bloating, constipation). Major deficiency in fat-soluble vitamins (A, D, E, K).",
                clinicalWarnings = listOf(
                    "BLEEDING DANGER: Impaired absorption of fat-soluble Vitamin K impairs coagulation factor synthesis, resulting in bruising and severe bleeding risk.",
                    "Resins significantly impair the absorption of other medications (e.g. Digoxin, Warfarin). Concomitant drugs must be spaced 1 hour before or 4-6 hours after Resins."
                )
            ),
            DrugClassData(
                id = "fibrates",
                name = "Fibric Acid Derivatives (Fibrates)",
                subtitle = "First-Line for Isolated Hypertriglyceridemia",
                indication = "Hypertriglyceridemia (Elevated VLDL & Triglycerides)",
                examples = listOf("Gemfibrozil", "Fenofibrate", "Bezafibrate", "Clofibrate"),
                moaSteps = listOf(
                    "Direct Action: Activates the vascular endothelial Lipoprotein Lipase (LPL) enzyme.",
                    "TG Degradation: Active LPL aggressively degrades circulating VLDL and Chylomicrons, directly lowering triglyceride levels.",
                    "Second-Gen Advantage: Second-generation Fibrates (Fenofibrate, Bezafibrate) also act as 'PPAR-alpha' gene activators. This triggers the cellular expression of ApoA-I & ApoA-II, raising protective HDL (good cholesterol) levels."
                ),
                kinetics = "Excretion: Excreted renally. Require dosing reductions in chronic kidney disease.\nDrug interactions: Displace Warfarin and Sulfonylureas from plasma proteins (raising bleeding and hypoglycemia risks).",
                adverseEffects = "GI discomfort, Gallstones (Lithiasis - due to increased cholesterol secretion into bile), Myopathy.",
                clinicalWarnings = listOf(
                    "DO NOT combine Gemfibrozil with Statins! Fenofibrate carries a much lower interaction profile and is preferred if combination is unavoidable.",
                    "Increased risk of gallstones (cholelithiasis); use with caution in gallbladder diseases."
                )
            ),
            DrugClassData(
                id = "niacin",
                name = "Lipolysis Inhibitors (Niacin / Nicotinic Acid)",
                subtitle = "The Most Potent Class for Raising Good HDL",
                indication = "Mixed Dyslipidemia, particularly to raise HDL",
                examples = listOf("Niacin", "Nicotinic Acid (Vitamin B3)"),
                moaSteps = listOf(
                    "Direct Action: Inhibits the intracellular hormone-sensitive lipase in adipose tissue.",
                    "VLDL Decrease: Stopping adipose tissue breakdown halts the flow of free fatty acids to the liver, thereby decreasing hepatic VLDL synthesis.",
                    "Systemic Effect: Secondary to lower VLDL, circulating Triglycerides and LDL levels drop. Simultaneously, Niacin decreases HDL clearance, making it the most potent drug for raising HDL."
                ),
                kinetics = "Absorption: Rapid oral absorption.\nDosing: Started at low doses and titrated slowly upwards to mitigate intense flushing side-effects. Taken with meals.",
                adverseEffects = "Cutaneous flushing (intense red, hot face), postural hypotension, pruritus, hyperuricemia (gout flareups), hyperglycemia.",
                clinicalWarnings = listOf(
                    "POSTURAL HYPOTENSION: Niacin stimulates prostaglandin synthesis leading to direct vasodilation. Combining with antihypertensives can cause severe orthostatic drops.",
                    "Myopathy risk increases if combined with Statins. Close monitoring and dose adjustment are required."
                )
            ),
            DrugClassData(
                id = "ezetimibe",
                name = "Sterol Absorption Inhibitors",
                subtitle = "Brush-Border Cholesterol Blockers",
                indication = "Hypercholesterolemia (Highly effective in combination)",
                examples = listOf("Ezetimibe"),
                moaSteps = listOf(
                    "Direct Action: Works locally at the brush border of the small intestine.",
                    "Absorption Blockade: Selectively binds to and blocks the NPC1L1 cholesterol transporter. This completely stops absorption of both dietary (exogenous) and biliary (endogenous) cholesterol.",
                    "Synergy Effect: Since blocking absorption alone does not stop the liver's internal synthesis (HMG-CoA is still active), combining Ezetimibe with a Statin stops BOTH synthesis and absorption for maximal efficacy."
                ),
                kinetics = "Metabolism: Glucuronidated to an active metabolite in the gut and liver, which circulates back to the intestine (enterohepatic recycling).",
                adverseEffects = "Mild GI side effects, headache. Risk of fat-soluble vitamin depletion and bleeding similar to Resins (though less frequent).",
                clinicalWarnings = listOf(
                    "When paired with Statins, the risk of hepatic transaminase elevations and myopathy increases. Regular liver function tests (LFTs) are recommended."
                )
            )
        )
    }

    // Filtered list based on search and category filters
    val filteredClasses = remember(searchQuery, filterClass) {
        drugClasses.filter { clazz ->
            val matchesSearch = clazz.name.contains(searchQuery, ignoreCase = true) ||
                    clazz.examples.any { it.contains(searchQuery, ignoreCase = true) } ||
                    clazz.indication.contains(searchQuery, ignoreCase = true)
            val matchesFilter = filterClass == null || clazz.id == filterClass
            matchesSearch && matchesFilter
        }
    }

    var expandedClassId by remember { mutableStateOf("statins") } // default expand statins

    Column(modifier = Modifier.fillMaxSize()) {
        // Sticky Header with Search and Category filters
        Surface(
            tonalElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.updateSearchQuery(it) },
                    placeholder = { Text("Search drugs (e.g. Statin, Fenofibrate...)") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    trailingIcon = if (searchQuery.isNotEmpty()) {
                        {
                            IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear")
                            }
                        }
                    } else null,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Fast category filters (horizontal scroll list)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    FilterChip(
                        selected = filterClass == null,
                        onClick = { viewModel.selectClassFilter(null) },
                        label = { Text("All Groups") }
                    )
                    drugClasses.forEach { clazz ->
                        val shortName = when (clazz.id) {
                            "statins" -> "Statins"
                            "resins" -> "Resins"
                            "fibrates" -> "Fibrates"
                            "niacin" -> "Niacin"
                            "ezetimibe" -> "Ezetimibe"
                            else -> clazz.name
                        }
                        FilterChip(
                            selected = filterClass == clazz.id,
                            onClick = { viewModel.selectClassFilter(if (filterClass == clazz.id) null else clazz.id) },
                            label = { Text(shortName) }
                        )
                    }
                }
            }
        }

        // Drug directory list
        if (filteredClasses.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.SearchOff,
                        contentDescription = "No results",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "No drugs matching your search",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                item {
                    DrugComparisonTableCard(
                        selectedClassId = filterClass,
                        onClassSelected = { classId ->
                            viewModel.selectClassFilter(if (filterClass == classId) null else classId)
                            if (classId != null) {
                                expandedClassId = classId
                            }
                        }
                    )
                }

                items(filteredClasses.size, key = { filteredClasses[it].id }) { index ->
                    val clazz = filteredClasses[index]
                    val isExpanded = expandedClassId == clazz.id
                    val savedNote = studyNotes.find { it.topicId == clazz.id }
                    val isBookmarkedFlow = viewModel.isBookmarkedFlow(clazz.id)
                    val isBookmarked by isBookmarkedFlow.collectAsState(initial = false)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isExpanded) MaterialTheme.colorScheme.surface
                            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        ),
                        border = BorderStroke(
                            1.dp,
                            if (isExpanded) MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                            else MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column {
                            // Header Row
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expandedClassId = if (isExpanded) "" else clazz.id }
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Group Icon
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            if (isExpanded) MaterialTheme.colorScheme.primaryContainer
                                            else MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
                                            shape = RoundedCornerShape(10.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = when (clazz.id) {
                                            "statins" -> Icons.Default.Block
                                            "resins" -> Icons.Default.CompareArrows
                                            "fibrates" -> Icons.Default.Speed
                                            "niacin" -> Icons.Default.LocalFireDepartment
                                            else -> Icons.Default.Shield
                                        },
                                        contentDescription = "Group Icon",
                                        tint = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = clazz.name,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = clazz.subtitle,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                // Bookmark & Expand Indicators
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = {
                                            viewModel.toggleBookmark(clazz.id, clazz.name, isBookmarked)
                                        },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                            contentDescription = "Bookmark Class",
                                            tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                                        )
                                    }
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                        contentDescription = "Expand info",
                                        tint = MaterialTheme.colorScheme.outline
                                    )
                                }
                            }

                            // Expanded Information View
                            if (isExpanded) {
                                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                                Column(modifier = Modifier.padding(16.dp)) {
                                    // Drug Examples Chip list
                                    Text("Key Examples:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        clazz.examples.forEach { item ->
                                            SuggestionChip(
                                                onClick = {},
                                                label = { Text(item, fontWeight = FontWeight.SemiBold) }
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Indication
                                    Text("Primary Indication:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    Text(clazz.indication, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // MOA Flow Diagram
                                    Text("Interactive Mode of Action (MOA):", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    InteractiveMoaSteps(steps = clazz.moaSteps)

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // Pharmacokinetics
                                    Text("Pharmacokinetics (PK):", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                                    ) {
                                        Text(
                                            text = clazz.kinetics,
                                            style = MaterialTheme.typography.bodySmall,
                                            lineHeight = 16.sp,
                                            modifier = Modifier.padding(10.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // Adverse Effects
                                    Text("Adverse Effects:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    Text(clazz.adverseEffects, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // Clinical Warnings Banner
                                    clazz.clinicalWarnings.forEach { warning ->
                                        Card(
                                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp)
                                        ) {
                                            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                                                Icon(Icons.Default.ErrorOutline, contentDescription = "Critical Warning", tint = Color(0xFFC62828), modifier = Modifier.size(18.dp))
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(warning, style = MaterialTheme.typography.bodySmall, color = Color(0xFFC62828), fontWeight = FontWeight.SemiBold, lineHeight = 15.sp)
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // Integrated Study Notes Drawer (Room)
                                    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                                    Spacer(modifier = Modifier.height(12.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.DriveFileRenameOutline, contentDescription = "Study Notes", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Personal Study Notes", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                                        }

                                        if (activeNoteTopic != clazz.id) {
                                            TextButton(
                                                onClick = { viewModel.openNoteEditor(clazz.id) }
                                            ) {
                                                Icon(if (savedNote == null) Icons.Default.Add else Icons.Default.Edit, contentDescription = "Edit Note", modifier = Modifier.size(16.dp))
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(if (savedNote == null) "Add Notes" else "Edit Notes", style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold))
                                            }
                                        }
                                    }

                                    if (activeNoteTopic == clazz.id) {
                                        // Edit interface
                                        OutlinedTextField(
                                            value = activeNoteText,
                                            onValueChange = { viewModel.updateNoteText(it) },
                                            placeholder = { Text("Type mnemonics, study shortcuts or exam tips for ${clazz.name}...") },
                                            modifier = Modifier.fillMaxWidth().height(100.dp).padding(vertical = 8.dp),
                                            textStyle = MaterialTheme.typography.bodySmall,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            TextButton(onClick = { viewModel.closeNoteEditor() }) {
                                                Text("Cancel")
                                            }
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Button(
                                                onClick = { viewModel.saveActiveNote() },
                                                shape = RoundedCornerShape(6.dp)
                                            ) {
                                                Icon(Icons.Default.Save, contentDescription = "Save", modifier = Modifier.size(16.dp))
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text("Save Note", style = androidx.compose.ui.text.TextStyle(fontSize = 11.sp))
                                            }
                                        }
                                    } else {
                                        // View interface
                                        if (savedNote != null) {
                                            Card(
                                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)),
                                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                                                shape = RoundedCornerShape(8.dp),
                                                modifier = Modifier.fillMaxWidth().padding(top = 6.dp)
                                            ) {
                                                Column(modifier = Modifier.padding(10.dp)) {
                                                    Text(
                                                        text = savedNote.content,
                                                        style = MaterialTheme.typography.bodySmall,
                                                        color = MaterialTheme.colorScheme.onSurface
                                                    )
                                                    Spacer(modifier = Modifier.height(6.dp))
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.End,
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = "Saved in Database",
                                                            style = MaterialTheme.typography.labelSmall,
                                                            color = MaterialTheme.colorScheme.primary,
                                                            fontWeight = FontWeight.SemiBold
                                                        )
                                                        Spacer(modifier = Modifier.width(8.dp))
                                                        IconButton(
                                                            onClick = { viewModel.deleteNoteDirectly(clazz.id) },
                                                            modifier = Modifier.size(24.dp)
                                                        ) {
                                                            Icon(Icons.Default.Delete, contentDescription = "Delete Note", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(16.dp))
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            Text(
                                                text = "No custom study notes added yet. Tap 'Add Notes' to write your own tips.",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.outline,
                                                modifier = Modifier.padding(top = 4.dp)
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
    }
}

@Composable
fun InteractiveMoaSteps(steps: List<String>) {
    var activeStepIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f), RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        // Step Indicators (Pills)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            steps.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .background(
                            color = if (index <= activeStepIndex) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(3.dp)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Step Label & Content
        Text(
            text = "Step ${activeStepIndex + 1} of ${steps.size}",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
        
        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            AnimatedContent(
                targetState = activeStepIndex,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                }
            ) { step ->
                Text(
                    text = steps[step],
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Back / Forward actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { if (activeStepIndex > 0) activeStepIndex -= 1 },
                enabled = activeStepIndex > 0
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Prev", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Previous")
            }

            TextButton(
                onClick = { if (activeStepIndex < steps.size - 1) activeStepIndex += 1 },
                enabled = activeStepIndex < steps.size - 1
            ) {
                Text("Next Step")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = "Next", modifier = Modifier.size(16.dp))
            }
        }
    }
}

data class DrugClassData(
    val id: String,
    val name: String,
    val subtitle: String,
    val indication: String,
    val examples: List<String>,
    val moaSteps: List<String>,
    val kinetics: String,
    val adverseEffects: String,
    val clinicalWarnings: List<String>
)

data class TableRowData(
    val id: String,
    val className: String,
    val badge: String,
    val badgeBg: Color,
    val badgeText: Color,
    val moa: String,
    val indication: String,
    val sideEffects: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun DrugComparisonTableCard(
    selectedClassId: String?,
    onClassSelected: (String?) -> Unit
) {
    var isExpanded by remember { mutableStateOf(true) }

    val rows = remember {
        listOf(
            TableRowData(
                id = "statins",
                className = "Statins",
                badge = "First-Line",
                badgeBg = Color(0xFFEADDFF),
                badgeText = Color(0xFF21005D),
                moa = "Inhibits HMG-CoA Reductase → Stops synthesis. Cell creates more surface LDL Receptors to pull cholesterol from blood.",
                indication = "Hypercholesterolemia (Elevated LDL & Cholesterol)",
                sideEffects = "Myopathy (muscle pain/weakness). High interaction risk with Gemfibrozil.",
                icon = Icons.Default.Block
            ),
            TableRowData(
                id = "resins",
                className = "Resins",
                badge = "GI Local",
                badgeBg = Color(0xFFE8DEF8),
                badgeText = Color(0xFF1D192B),
                moa = "Not absorbed systemically. Binds bile in gut, causing fecal excretion. Liver utilizes cholesterol stores to make new bile, upregulating LDL receptors.",
                indication = "Hypercholesterolemia (Type IIa / IIb)",
                sideEffects = "Severe GI distress (constipation, bloating). Depletes fat-soluble Vitamin K → BLEEDING risk.",
                icon = Icons.Default.CompareArrows
            ),
            TableRowData(
                id = "fibrates",
                className = "Fibrates",
                badge = "LPL Activator",
                badgeBg = Color(0xFFD1E1FF),
                badgeText = Color(0xFF1D4ED8),
                moa = "Activates Lipoprotein Lipase (LPL) to aggressively degrade circulating triglycerides. PPAR-alpha activation raises protective HDL.",
                indication = "Hypertriglyceridemia (Elevated VLDL & TGs)",
                sideEffects = "GI discomfort, Gallstones (Lithiasis), Myopathy (extreme if paired with Gemfibrozil).",
                icon = Icons.Default.Speed
            ),
            TableRowData(
                id = "niacin",
                className = "Niacin",
                badge = "Potent HDL ↑",
                badgeBg = Color(0xFFFFD8E4),
                badgeText = Color(0xFF31111D),
                moa = "Inhibits hormone-sensitive lipase in adipose. Halts VLDL & LDL synthesis. Slows HDL clearance (highest HDL-raising effect).",
                indication = "Mixed Dyslipidemia, particularly to raise HDL",
                sideEffects = "Intense cutaneous flushing (hot/red face), postural hypotension, hyperuricemia (gout), hyperglycemia.",
                icon = Icons.Default.LocalFireDepartment
            ),
            TableRowData(
                id = "ezetimibe",
                className = "Ezetimibe",
                badge = "Absorb Block",
                badgeBg = Color(0xFFB6F2AF),
                badgeText = Color(0xFF166534),
                moa = "Blocks NPC1L1 cholesterol transporter in intestinal brush-border, preventing absorption of dietary and biliary cholesterol.",
                indication = "Hypercholesterolemia (highly synergistic with Statins)",
                sideEffects = "Mild GI side effects, headache. Minor fat-soluble vitamin depletion and bleeding risk.",
                icon = Icons.Default.Shield
            )
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column {
            // Header Row clickable
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ListAlt,
                            contentDescription = "Matrix Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "Antihyperlipidemic Classes Matrix",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Quick comparison of 5 main drug groups",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Toggle Expand",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }

            if (isExpanded) {
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.12f))

                // The Table horizontally scrollable
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(bottom = 12.dp)
                ) {
                    Column(modifier = Modifier.width(900.dp)) { // Total width sum of columns
                        // Table Header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                                .padding(vertical = 10.dp, horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Drug Class",
                                modifier = Modifier.width(180.dp),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Primary Mechanism (MOA)",
                                modifier = Modifier.width(280.dp),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Main Indication",
                                modifier = Modifier.width(200.dp),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Common Side Effects",
                                modifier = Modifier.width(240.dp),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Table Rows
                        rows.forEachIndexed { index, row ->
                            val isSelected = selectedClassId == row.id
                            val rowBgColor = when {
                                isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                                index % 2 == 0 -> Color.Transparent
                                else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.15f)
                            }
                            
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(rowBgColor)
                                    .clickable { onClassSelected(row.id) }
                                    .padding(vertical = 12.dp, horizontal = 12.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                // Class Column
                                Row(
                                    modifier = Modifier.width(180.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = row.icon,
                                        contentDescription = row.className,
                                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Column {
                                        Text(
                                            text = row.className,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Card(
                                            colors = CardDefaults.cardColors(containerColor = row.badgeBg),
                                            shape = RoundedCornerShape(6.dp)
                                        ) {
                                            Text(
                                                text = row.badge,
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.SemiBold,
                                                color = row.badgeText,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                fontSize = 9.sp
                                            )
                                        }
                                    }
                                }

                                // MOA Column
                                Text(
                                    text = row.moa,
                                    modifier = Modifier.width(280.dp).padding(end = 12.dp),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    lineHeight = 15.sp
                                )

                                // Indication Column
                                Column(modifier = Modifier.width(200.dp).padding(end = 12.dp)) {
                                    val isTG = row.id == "fibrates"
                                    val isHDL = row.id == "niacin"
                                    val targetBadgeBg = when {
                                        isTG -> Color(0xFFFFF3E0)
                                        isHDL -> Color(0xFFFCE4EC)
                                        else -> Color(0xFFE3F2FD)
                                    }
                                    val targetBadgeText = when {
                                        isTG -> Color(0xFFE65100)
                                        isHDL -> Color(0xFFC2185B)
                                        else -> Color(0xFF1565C0)
                                    }
                                    val targetLabel = when {
                                        isTG -> "Triglycerides"
                                        isHDL -> "Raises HDL"
                                        else -> "LDL Cholesterol"
                                    }
                                    
                                    Text(
                                        text = row.indication,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        lineHeight = 15.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = targetBadgeBg),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = targetLabel,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = targetBadgeText,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp),
                                            fontSize = 8.sp
                                        )
                                    }
                                }

                                // Side Effects Column
                                Text(
                                    text = row.sideEffects,
                                    modifier = Modifier.width(240.dp).padding(end = 4.dp),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 15.sp
                                )
                            }
                            if (index < rows.size - 1) {
                                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.08f), modifier = Modifier.padding(horizontal = 12.dp))
                            }
                        }
                    }
                }
                
                // Active Interactive Hint
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f))
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Interactive Hint",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Tip: Tap on any drug class row above to instantly filter the detailed index below!",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
