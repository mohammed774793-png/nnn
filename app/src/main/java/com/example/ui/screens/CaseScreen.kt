package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.PharmaViewModel

@Composable
fun CaseScreen(viewModel: PharmaViewModel) {
    val step by viewModel.caseStep.collectAsState()
    val selectedOption by viewModel.selectedTreatmentOption.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Section Title
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.School, contentDescription = "Case Study", tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Clinical Case Challenge",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = "Test your clinical judgment with the lecture's real-world patient case.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedContent(
            targetState = step,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            }
        ) { currentStep ->
            when (currentStep) {
                0 -> CaseDescriptionView(onProceed = { viewModel.nextCaseStep() })
                1 -> CaseDecisionView(
                    selectedOption = selectedOption,
                    onOptionSelect = { viewModel.selectTreatmentOption(it) },
                    onSubmit = { viewModel.submitTreatmentOption() }
                )
                2 -> DoctorVerdictView(
                    selectedOption = selectedOption,
                    onRestart = { viewModel.resetCase() }
                )
            }
        }
    }
}

@Composable
fun CaseDescriptionView(onProceed: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Patient Info Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Patient Profile: Case #104",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "DIABETIC PATIENT",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "A 56-year-old diabetic patient presents to the cardiology clinic for a routine cardiovascular screening. The patient is diagnosed with secondary hyperlipidemia due to metabolic impairment from long-standing Diabetes. Below are the lipid panel lab estimates:",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Lab results list styled like a medical chart
            Text(
                text = "BLOOD LABORATORY REPORT",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(6.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.15f))
            ) {
                LabResultRow("Triglycerides (TGs)", "380 mg/dL", "ELEVATED", isHigh = true, normalRange = "< 150 mg/dL")
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                LabResultRow("HDL-Cholesterol ('Good')", "32 mg/dL", "LOW", isHigh = true, normalRange = "> 40 mg/dL", labelColor = Color(0xFFC62828))
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                LabResultRow("LDL-Cholesterol ('Bad')", "104 mg/dL", "NEAR NORMAL", isHigh = false, normalRange = "< 100 mg/dL")
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                LabResultRow("Total Cholesterol", "195 mg/dL", "NORMAL", isHigh = false, normalRange = "< 200 mg/dL")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Summary observation
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = "Observation", tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Key Clinical Findings: High Triglycerides, Low good HDL, but near-normal bad LDL and cholesterol.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onProceed,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Analyze Options & Make Decision", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = "Next")
            }
        }
    }
}

@Composable
fun LabResultRow(
    name: String,
    value: String,
    status: String,
    isHigh: Boolean,
    normalRange: String,
    labelColor: Color = Color.Unspecified
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
            Text("Ref Range: $normalRange", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
        }
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = value,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(end = 8.dp)
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isHigh) {
                        if (labelColor == Color.Unspecified) Color(0xFFFFF3E0) else Color(0xFFFFEBEE)
                    } else Color(0xFFE8F5E9)
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = status,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = if (isHigh) {
                        if (labelColor == Color.Unspecified) Color(0xFFE65100) else Color(0xFFC62828)
                    } else Color(0xFF2E7D32),
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
fun CaseDecisionView(
    selectedOption: String?,
    onOptionSelect: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Clinical Problem Formulation:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Considering this is a diabetic patient with high triglycerides, low HDL, but near-normal LDL, what is the FIRST-LINE drug of choice for this patient?",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Option A: Statins
            OptionCard(
                optionId = "statins",
                title = "A. HMG-CoA Reductase Inhibitors (Statins)",
                subtitle = "Samir's Suggestion",
                description = "Prescribe a standard or high-intensity Statin (e.g. Atorvastatin) to target cardiovascular risk, despite near-normal baseline LDL cholesterol.",
                selected = selectedOption == "statins",
                onSelect = { onOptionSelect("statins") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Option B: Fibrates
            OptionCard(
                optionId = "fibrates",
                title = "B. Second-Generation Fibrates (e.g. Fenofibrate)",
                subtitle = "Saleh's Suggestion",
                description = "Prescribe a Fibrate to target and degrade the high triglycerides (via LPL activation) and raise the low good HDL (via PPAR-alpha gene activation).",
                selected = selectedOption == "fibrates",
                onSelect = { onOptionSelect("fibrates") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Option C: Niacin
            OptionCard(
                optionId = "niacin",
                title = "C. Lipolysis Inhibitors (Niacin / Nicotinic Acid)",
                subtitle = "Samir's Alternative",
                description = "Prescribe Niacin to heavily suppress adipose tissue breakdown, lower triglyceride-VLDL secretion, and act as the most potent agent to raise HDL.",
                selected = selectedOption == "niacin",
                onSelect = { onOptionSelect("niacin") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onSubmit,
                enabled = selectedOption != null,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Submit Clinical Decision", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.Verified, contentDescription = "Check")
            }
        }
    }
}

@Composable
fun OptionCard(
    optionId: String,
    title: String,
    subtitle: String,
    description: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)
        ),
        border = BorderStroke(
            width = if (selected) 2.dp else 1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
                    Text(subtitle, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                }
                RadioButton(
                    selected = selected,
                    onClick = onSelect
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
fun DoctorVerdictView(
    selectedOption: String?,
    onRestart: () -> Unit
) {
    val isCorrect = selectedOption == "statins"

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Correct / Incorrect
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            if (isCorrect) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                        contentDescription = "Verdict",
                        tint = if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = if (isCorrect) "EXCELLENT DECISION!" else "DIAGNOSTIC INSIGHT DETECTED",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)
                    )
                    Text(
                        text = if (isCorrect) "You chose Statins correctly." else "Let's review the physiological outcomes.",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "The Doctor's Verdict & Lecture Proof:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.12f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "For Diabetic Patients, STATINS (HMG-CoA Reductase Inhibitors) are ALWAYS the First-Line drug of choice, even if baseline cholesterol/LDL is normal and triglycerides are high!",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Why? Because Statins have solid, extensive clinical trial evidence proving they directly prevent cardiovascular events (stroke, myocardial infarction) and protect the diabetic vasculature. Fibrates and Niacin, although cosmetically normalizing triglyceride and HDL laboratory levels, lack clear medical evidence for preventing cardiovascular complications in diabetic patients.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Clinical Rules table
            Text("High-Yield Lecture Pearls:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(8.dp))

            ClinicalPearlItem(
                title = "Mixed Dyslipidemia Strategy",
                description = "If statins alone are insufficient to control lipid profiles, combination therapy can be utilized (e.g. adding Ezetimibe or Fenofibrate)."
            )
            ClinicalPearlItem(
                title = "Absolute Safe Guard",
                description = "NEVER combine any Statin with Gemfibrozil (Fibrate) due to an extreme risk of severe, life-threatening myopathy and rhabdomyolysis."
            )
            ClinicalPearlItem(
                title = "When are Fibrates 1st-Line?",
                description = "Fibrates are first-line ONLY if: \n1. There is severe, isolated hypertriglyceridemia (where cholesterol is completely normal—which is rare).\n2. Statins are absolutely contraindicated or completely untolerated."
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = onRestart,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Restart")
                Spacer(modifier = Modifier.width(6.dp))
                Text("Restart Simulation", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ClinicalPearlItem(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            Icons.Default.Lightbulb,
            contentDescription = "Pearl",
            tint = Color(0xFFFFA726),
            modifier = Modifier
                .size(20.dp)
                .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
            Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 15.sp)
        }
    }
}
