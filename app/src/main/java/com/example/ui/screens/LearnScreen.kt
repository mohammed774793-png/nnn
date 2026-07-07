package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.PharmaViewModel

@Composable
fun LearnScreen(viewModel: PharmaViewModel) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp)
    ) {
        // Hero Image banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_pharma_hero_1783211383422),
                contentDescription = "Lipid Pharmacology banner",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background.copy(alpha = 0.95f))
                        )
                    )
            )
            // Title text overlay
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Cardiovascular Pharmacology",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Antihyperlipidemic Drugs",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            // Personalized Lecture Credit Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Author",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "Yousif Mahmoud Alsobay ♡",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Lecturer ID: ♪774793171",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Today we introduce Cardiovascular Pharmacology: medications managing Hyperlipidemia.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Side Note",
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Side note: Antimicrobial section is handled by our colleague. Antimalarials study is postponed for a later reading session.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }
            }

            // Lecture Introduction Cards
            Text(
                text = "1. Introduction to Hyperlipidemia",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Hyperlipidemia is defined as elevated levels of cholesterol or triglycerides in the bloodstream. While within normal limits, these lipids are physiologically essential, pathological elevations lead to atherosclerotic vascular complications.",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                // Cholesterol Role Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Waves, contentDescription = "Cell Membrane", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Cholesterol", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "• Phospholipid cell membrane constituent.\n• Precursor for sex hormone synthesis.",
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = 16.sp
                        )
                    }
                }

                // Triglycerides Role Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Bolt, contentDescription = "Energy Source", tint = Color(0xFFE65100), modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Triglycerides", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "• Packaged in chylomicrons/VLDL.\n• Serves as the major system-wide energy source.",
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Complications Alert Panel
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Warning, 
                        contentDescription = "Clinical Warning", 
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Atherosclerotic Cascade",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Text(
                            text = "Elevated circulating lipids lead to Atherosclerosis (hardening of arteries), which causes hypertension, ischemic stroke, angina, and myocardial infarction. Therapeutic goal is to prevent this cascade.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.9f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section 2: Lipoproteins
            Text(
                text = "2. Lipoproteins (Lipid Carriers)",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Since lipids are hydrophobic and blood is aqueous, lipids must couple with hydrophilic apoproteins to form soluble Lipoproteins for circulatory transport.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Interactive Structural Canvas
            Text(
                text = "Interactive Cross-Section: Lipoprotein Structure",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            LipoproteinVisualizer()

            Spacer(modifier = Modifier.height(20.dp))

            LipoproteinSpectrumChart()

            Spacer(modifier = Modifier.height(20.dp))

            // Good vs Bad interactive Toggle
            LipoproteinCarrierSelector()

            Spacer(modifier = Modifier.height(24.dp))

            // Section 3: Types of Hyperlipidemia
            Text(
                text = "3. Clinical Classifications",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))

            HyperlipidemiaTypesCards()

            Spacer(modifier = Modifier.height(24.dp))

            ClinicalCaseAlertFooter(onNavigateToCase = { viewModel.selectTab("case") })

            Spacer(modifier = Modifier.height(24.dp))

            InteractiveLectureQuiz()
        }
    }
}

@Composable
fun LipoproteinVisualizer() {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val onSurface = MaterialTheme.colorScheme.onSurface

    var selectedPart by remember { mutableStateOf("all") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                // Drawing of Lipoprotein
                Canvas(modifier = Modifier.size(160.dp)) {
                    val center = Offset(size.width / 2, size.height / 2)
                    
                    // Core (containing Triglycerides & Cholesteryl Esters)
                    val coreColor = if (selectedPart == "all" || selectedPart == "core") Color(0xFFFFA726) else Color(0xFFFFA726).copy(alpha = 0.3f)
                    drawCircle(
                        color = coreColor,
                        radius = size.width / 3,
                        center = center
                    )

                    // Outer Phospholipid monolayer
                    val shellColor = if (selectedPart == "all" || selectedPart == "shell") Color(0xFF26A69A) else Color(0xFF26A69A).copy(alpha = 0.3f)
                    drawCircle(
                        color = shellColor,
                        radius = size.width / 2.1f,
                        center = center,
                        style = Stroke(width = 18f)
                    )

                    // Free Cholesterols embedded in membrane
                    val cholesterolColor = if (selectedPart == "all" || selectedPart == "chol") Color(0xFFEF5350) else Color(0xFFEF5350).copy(alpha = 0.3f)
                    for (i in 0 until 6) {
                        val angle = (i * 60) * (Math.PI / 180f)
                        val r = size.width / 2.1f
                        val cx = center.x + r * Math.cos(angle).toFloat()
                        val cy = center.y + r * Math.sin(angle).toFloat()
                        drawCircle(
                            color = cholesterolColor,
                            radius = 10f,
                            center = Offset(cx, cy)
                        )
                    }

                    // Apoproteins (ApoB, ApoE, ApoA) receptors on shell surface
                    val apoColor = if (selectedPart == "all" || selectedPart == "apo") Color(0xFF7E57C2) else Color(0xFF7E57C2).copy(alpha = 0.3f)
                    
                    // Apoprotein 1
                    drawCircle(
                        color = apoColor,
                        radius = 20f,
                        center = Offset(center.x - size.width / 2.3f, center.y - size.width / 8f)
                    )
                    // Apoprotein 2
                    drawCircle(
                        color = apoColor,
                        radius = 16f,
                        center = Offset(center.x + size.width / 3.1f, center.y - size.width / 3.1f)
                    )
                }

                // Labels overlay on canvas
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        "Apoprotein",
                        color = Color(0xFF7E57C2),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                    Text(
                        "Phospholipids",
                        color = Color(0xFF26A69A),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                    Text(
                        "Hydrophobic Core\n(Triglycerides & Cholesterol)",
                        color = Color(0xFFE65100),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Text(
                        "Free Cholesterol",
                        color = Color(0xFFEF5350),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.align(Alignment.BottomStart)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Interactive Selector buttons for structural parts
            Text(
                text = "Tap components below to analyze physiological roles:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf(
                    Triple("all", "Show All", MaterialTheme.colorScheme.outline),
                    Triple("core", "Core Lipids", Color(0xFFFFA726)),
                    Triple("shell", "Outer Shell", Color(0xFF26A69A)),
                    Triple("apo", "Apoproteins", Color(0xFF7E57C2))
                ).forEach { (id, label, color) ->
                    OutlinedButton(
                        onClick = { selectedPart = id },
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = if (selectedPart == id) {
                            ButtonDefaults.outlinedButtonColors(
                                containerColor = color.copy(alpha = 0.15f),
                                contentColor = color
                            )
                        } else {
                            ButtonDefaults.outlinedButtonColors(contentColor = onSurface.copy(alpha = 0.7f))
                        },
                        border = BorderStroke(1.dp, if (selectedPart == id) color else onSurface.copy(alpha = 0.15f))
                    ) {
                        Text(label, style = androidx.compose.ui.text.TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Detailed card explaining the selected part
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    val desc = when (selectedPart) {
                        "core" -> "The core is strictly hydrophobic. It carries Cholesterol Esters and Triglycerides. Triglycerides serve as cell fuel, whereas cholesteryl esters are carried to tissues to build phospholipid membranes or hormones."
                        "shell" -> "The outer surface is amphipathic. It is made of a phospholipid monolayer that creates a protective barrier, allowing non-water-soluble lipids inside the core to disperse and travel smoothly in public vascular channels."
                        "apo" -> "Apoproteins are active structural proteins on the shell. They acts as cell 'keys' (ligands) that define exactly which receptor (e.g. LDL receptor) the carrier binds to. This determines how lipids are targeted and cleared from the bloodstream."
                        else -> "A lipoprotein is composed of a non-polar Core (Triglycerides, Cholesteryl Esters) and an amphipathic Shell (Phospholipids, Free Cholesterol, Apoproteins). Soluble in water, it transports lipid cargos throughout the arterial highway system."
                    }
                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun LipoproteinCarrierSelector() {
    // 6 Lipoproteins
    val lipoproteins = listOf(
        LipoproteinData(
            name = "Chylomicrons",
            density = "Very Low Density (<0.95 g/mL)",
            size = "Largest (100–1000 nm)",
            primaryCore = "Triglycerides (90%)",
            clinicalRole = "Carries dietary lipids from gut to tissues. High level indicates Hypertriglyceridemia.",
            isGood = false
        ),
        LipoproteinData(
            name = "Chylomicron remnants",
            density = "Low Density (0.95–1.006 g/mL)",
            size = "Medium-Large (30–80 nm)",
            primaryCore = "Cholesterol & Triglycerides",
            clinicalRole = "Bile-cleared remnant of chylomicrons containing dietary cholesterol. Atherogenic.",
            isGood = false
        ),
        LipoproteinData(
            name = "VLDL (Very Low-Density)",
            density = "Very Low (0.95–1.006 g/mL)",
            size = "Large (30–80 nm)",
            primaryCore = "Triglycerides (55%)",
            clinicalRole = "Synthesized in the liver to deliver endogenous Triglycerides to tissues. High level indicates Hypertriglyceridemia.",
            isGood = false
        ),
        LipoproteinData(
            name = "IDL (Intermediate-Density)",
            density = "Intermediate (1.006–1.019 g/mL)",
            size = "Medium (25–35 nm)",
            primaryCore = "Triglycerides & Cholesterol",
            clinicalRole = "Transition state between VLDL and LDL. Highly atherogenic remnant.",
            isGood = false
        ),
        LipoproteinData(
            name = "LDL (Low-Density Lipoprotein)",
            density = "Low Density (1.019–1.063 g/mL)",
            size = "Small (18–25 nm)",
            primaryCore = "Cholesterol (50%)",
            clinicalRole = "The primary 'Bad Lipoprotein'. Delivers cholesterol to tissues. Elevated LDL indicates Hypercholesterolemia and directly drives plaque formations.",
            isGood = false
        ),
        LipoproteinData(
            name = "HDL (High-Density Lipoprotein)",
            density = "Highest Density (>1.063 g/mL)",
            size = "Smallest (5–12 nm)",
            primaryCore = "Protein (50%) & Phospholipids",
            clinicalRole = "The 'Good Lipoprotein'. Serves as vascular scavenger, collecting lipid waste deposited on artery walls and transporting it to liver for bile excretion. Low HDL is a major cardiac risk factor.",
            isGood = true
        )
    )

    var expandedIndex by remember { mutableStateOf(5) } // Default expand HDL (Good)

    Text(
        text = "The 6 Lipoprotein Carrier Classes",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "As lipoprotein size decreases, protein density increases. Differentiating the elevated class is vital for correct pharmacotherapy selection.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Spacer(modifier = Modifier.height(10.dp))

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        lipoproteins.forEachIndexed { index, lp ->
            val isExpanded = expandedIndex == index
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedIndex = if (isExpanded) -1 else index },
                colors = CardDefaults.cardColors(
                    containerColor = if (lp.isGood) {
                        if (isExpanded) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                        else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)
                    } else {
                        if (isExpanded) MaterialTheme.colorScheme.surfaceVariant
                        else MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                    }
                ),
                border = BorderStroke(
                    1.dp, 
                    if (isExpanded) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) 
                    else MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(if (lp.isGood) Color(0xFF26A69A) else Color(0xFFEF5350))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = lp.name,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (lp.isGood) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                        
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if (lp.isGood) Color(0xFFE0F2F1) else Color(0xFFFFEBEE)
                            ),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = if (lp.isGood) "GOOD (Scavenger)" else "BAD (Atherogenic)",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (lp.isGood) Color(0xFF00796B) else Color(0xFFC62828),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    if (isExpanded) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f)) {
                                textLabelValue("Density", lp.density)
                                Spacer(modifier = Modifier.height(4.dp))
                                textLabelValue("Size Range", lp.size)
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                textLabelValue("Primary Core Lipid", lp.primaryCore)
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Clinical Description:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = lp.clinicalRole,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                            lineHeight = 15.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun textLabelValue(label: String, value: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.secondary
    )
    Text(
        text = value,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
    )
}

data class LipoproteinData(
    val name: String,
    val density: String,
    val size: String,
    val primaryCore: String,
    val clinicalRole: String,
    val isGood: Boolean
)

@Composable
fun HyperlipidemiaTypesCards() {
    var selectedTab by remember { mutableStateOf(0) } // 0 = Primary (Genetic), 1 = Secondary (Disease/Drug)

    Column {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Primary (Genetic)", fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Secondary (Acquired)", fontWeight = FontWeight.Bold) }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        AnimatedContent(
            targetState = selectedTab,
            transitionSpec = {
                slideInHorizontally { width -> if (targetState > initialState) width else -width } togetherWith
                        slideOutHorizontally { width -> if (targetState > initialState) -width else width }
            }
        ) { tabIndex ->
            if (tabIndex == 0) {
                // Primary Genetic
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Primary Hyperlipidemia results from genetic alterations affecting lipid production, enzymes, or carrier receptors. This is often 'Familial' and hereditary.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Science, contentDescription = "Genetic Defect", tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Common Genetic Subtypes & Phenotypes", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            // Type 2a & 2b Card
                            Card(
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text("Type IIa & IIb (Familial Hypercholesterolemia)", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("• Defect: LDL receptor mutation (impaired blood clearance).\n• Elevated lipid: LDL is extremely high (Cholesterol).\n• Clinical aspect: High atherosclerotic cardiovascular risk.", style = MaterialTheme.typography.bodySmall)
                                }
                            }

                            // Type 4 Card
                            Card(
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text("Type IV (Familial Hypertriglyceridemia)", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("• Defect: LPL deficiency or VLDL overproduction.\n• Elevated lipid: VLDL is extremely high (Triglycerides).\n• Clinical aspect: Can progress to acute pancreatitis.", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            } else {
                // Secondary Acquired
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Secondary Hyperlipidemia occurs as an indirect consequence of chronic underlying diseases or as a side-effect of various medications.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Healing, contentDescription = "Diseases", tint = Color(0xFF00796B), modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Disease-Induced", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "• Diabetes Mellitus (insulin deficiency impairs lipid storage, triggering high VLDL).\n• Hypothyroidism (decreased thyroid levels reduce hepatic LDL receptor expression).\n• Nephrotic Syndrome (increased hepatic synthesis).",
                                    style = MaterialTheme.typography.bodySmall,
                                    lineHeight = 16.sp
                                )
                            }
                        }

                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Medication, contentDescription = "Drugs", tint = Color(0xFFC62828), modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Drug-Induced", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "• Corticosteroids (increases systemic lipid transport).\n• Beta-blockers (impair LPL activity, raising triglycerides).\n• Oral Contraceptives / Estrogens (raise hepatic VLDL secretion).",
                                    style = MaterialTheme.typography.bodySmall,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LipoproteinSpectrumChart() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFCAC4D0), RoundedCornerShape(28.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(28.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "LIPOPROTEIN DENSITY SPECTRUM",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    letterSpacing = 1.5.sp
                )
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8DEF8)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "6 Types",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF49454F),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // CHYLO
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(96.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color(0xFFFFD8E4))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "CHYLO",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 9.sp
                    )
                }

                // VLDL
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(64.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color(0xFFFFD8E4).copy(alpha = 0.8f))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "VLDL",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 9.sp
                    )
                }

                // IDL
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color(0xFFFFD8E4).copy(alpha = 0.6f))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "IDL",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        fontSize = 9.sp
                    )
                }

                // LDL
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color(0xFFD1E1FF))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "LDL",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1D4ED8),
                        fontSize = 9.sp
                    )
                }

                // HDL
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(24.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color(0xFFB6F2AF))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "HDL",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF166534),
                        fontSize = 9.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFCAC4D0).copy(alpha = 0.5f))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Larger / Lower Density",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF49454F),
                    fontSize = 10.sp
                )
                Text(
                    text = "Density Increase →",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF49454F),
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun ClinicalCaseAlertFooter(onNavigateToCase: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToCase() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F2FA)),
        border = BorderStroke(2.dp, Color(0xFF6750A4).copy(alpha = 0.1f)),
        shape = RoundedCornerShape(28.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFD8E4)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Case Info",
                    tint = Color(0xFF31111D),
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Clinical Case Alert",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF31111D)
                )
                Text(
                    text = "Diabetic + Elevated TGs + Low HDL. What is the First-Line?",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF31111D).copy(alpha = 0.7f)
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF6750A4)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Go to Case",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

data class LectureQuizQuestion(
    val questionText: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

@Composable
fun InteractiveLectureQuiz() {
    val questions = remember {
        listOf(
            LectureQuizQuestion(
                questionText = "Which drug combination is absolutely contraindicated with Statins due to an extremely high risk of severe myopathy?",
                options = listOf(
                    "Ezetimibe",
                    "Gemfibrozil (a Fibrate)",
                    "Cholestyramine (a Resin)",
                    "Vitamin K supplementation"
                ),
                correctIndex = 1,
                explanation = "Statins must NEVER be combined with Gemfibrozil due to a very high risk of severe, potentially life-threatening myopathy (muscle pain and weakness)."
            ),
            LectureQuizQuestion(
                questionText = "A patient taking a bile acid sequestrant (resin) presents with unexpected bleeding. What is the physiological cause of this adverse effect?",
                options = listOf(
                    "Direct systemic inhibition of platelet aggregation",
                    "Impaired Vitamin K absorption leading to a deficiency in clotting factors",
                    "Liver toxicity resulting in a sudden drop in albumin synthesis",
                    "Upregulation of surface LDL receptors causing bleeding"
                ),
                correctIndex = 1,
                explanation = "Bile acid sequestrants are not absorbed and act locally in the GI tract, but they impair the absorption of fat-soluble vitamins (A, D, E, K). Deficiency in Vitamin K leads directly to a risk of bleeding."
            ),
            LectureQuizQuestion(
                questionText = "A patient experiences intense cutaneous flushing and hot face after starting high-dose Niacin. What can minimize this reaction?",
                options = listOf(
                    "Taking the medication with meals and gradually titrating/increasing the dosage",
                    "Combining Niacin with high doses of Gemfibrozil",
                    "Administering the dose strictly with fat-soluble vitamins",
                    "Switching immediately to a first-generation resin like Clofibrate"
                ),
                correctIndex = 0,
                explanation = "Niacin-induced flushing is mediated by prostaglandin release. It can be minimized by taking the medication with meals and titrating up the dose gradually over time."
            ),
            LectureQuizQuestion(
                questionText = "What is the first-line antihyperlipidemic drug of choice for a Diabetic patient with elevated Triglycerides and low HDL, but near-normal LDL?",
                options = listOf(
                    "Second-generation Fibrates (e.g., Fenofibrate) to target triglycerides",
                    "HMG-CoA Reductase Inhibitors (Statins) for system-wide cardioprotection",
                    "Nicotinic Acid (Niacin) to selectively raise low HDL",
                    "Bile Acid Sequestrants (Resins) to avoid renal clearance"
                ),
                correctIndex = 1,
                explanation = "For diabetic patients with secondary hyperlipidemia, Statins are ALWAYS the first-line drug of choice due to extensive, solid clinical evidence proving their ability to protect diabetic patients from cardiovascular events."
            ),
            LectureQuizQuestion(
                questionText = "Why is Ezetimibe highly synergistic and commonly co-prescribed with a Statin?",
                options = listOf(
                    "They both bind bile acids in the gut to prevent fat-soluble vitamin depletion",
                    "Ezetimibe activates Cytochrome 3A4 to accelerate liver metabolism",
                    "It simultaneously blocks intestinal cholesterol absorption while the statin blocks hepatic synthesis",
                    "They bind to form a non-absorbable chemical complex in the feces"
                ),
                correctIndex = 2,
                explanation = "Since blocking intestinal absorption alone doesn't stop the liver's internal cholesterol synthesis, combining Ezetimibe (absorption inhibitor) with a Statin (synthesis inhibitor) blocks both pathways, resulting in high synergy."
            )
        )
    }

    var currentIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var isAnswered by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }
    var quizCompleted by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), RoundedCornerShape(24.dp))
            .animateContentSize(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Assignment,
                        contentDescription = "Quiz Icon",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Lecture Quick Quiz",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (!quizCompleted) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Q ${currentIndex + 1}/${questions.size}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!quizCompleted) {
                val currentQuestion = questions[currentIndex]

                // Progress Bar
                LinearProgressIndicator(
                    progress = (currentIndex.toFloat() + if (isAnswered) 1.0f else 0.0f) / questions.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = currentQuestion.questionText,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    currentQuestion.options.forEachIndexed { optIndex, optionText ->
                        val isSelected = selectedOption == optIndex
                        val isCorrect = optIndex == currentQuestion.correctIndex

                        val containerColor = when {
                            isAnswered && isCorrect -> Color(0xFFE8F5E9) // correct option highlighted in green
                            isAnswered && isSelected && !isCorrect -> Color(0xFFFFEBEE) // selected incorrect highlighted in red
                            isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                            else -> MaterialTheme.colorScheme.surface
                        }

                        val borderColor = when {
                            isAnswered && isCorrect -> Color(0xFF4CAF50)
                            isAnswered && isSelected && !isCorrect -> Color(0xFFE53935)
                            isSelected -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                        }

                        val textStyle = if (isSelected || (isAnswered && isCorrect)) {
                            MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        } else {
                            MaterialTheme.typography.bodyMedium
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, borderColor, RoundedCornerShape(16.dp))
                                .clickable(enabled = !isAnswered) { selectedOption = optIndex },
                            colors = CardDefaults.cardColors(containerColor = containerColor),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    // Bullet indicator
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(
                                                    alpha = 0.15f
                                                )
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = when (optIndex) {
                                                0 -> "A"
                                                1 -> "B"
                                                2 -> "C"
                                                else -> "D"
                                            },
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Text(
                                        text = optionText,
                                        style = textStyle,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        lineHeight = 18.sp
                                    )
                                }

                                if (isAnswered) {
                                    if (isCorrect) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "Correct",
                                            tint = Color(0xFF2E7D32),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    } else if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Incorrect",
                                            tint = Color(0xFFC62828),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Answer explanation if answered
                if (isAnswered) {
                    val userCorrect = selectedOption == currentQuestion.correctIndex
                    val explanationBg = if (userCorrect) Color(0xFFE8F5E9).copy(alpha = 0.5f) else Color(0xFFFFEBEE).copy(alpha = 0.5f)
                    val explanationBorder = if (userCorrect) Color(0xFF81C784) else Color(0xFFE57373)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, explanationBorder, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = explanationBg),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = if (userCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                    contentDescription = "Feedback",
                                    tint = if (userCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = if (userCorrect) "Correct Answer!" else "Incorrect Answer",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (userCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = currentQuestion.explanation,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Action Button
                Button(
                    onClick = {
                        if (!isAnswered) {
                            isAnswered = true
                            if (selectedOption == currentQuestion.correctIndex) {
                                score++
                            }
                        } else {
                            if (currentIndex < questions.size - 1) {
                                currentIndex++
                                selectedOption = null
                                isAnswered = false
                            } else {
                                quizCompleted = true
                            }
                        }
                    },
                    enabled = selectedOption != null,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = when {
                            !isAnswered -> "Submit Answer"
                            currentIndex < questions.size - 1 -> "Next Question"
                            else -> "Finish & View Score"
                        },
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelLarge
                    )
                }

            } else {
                // Quiz Completed Screen
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = "Trophy",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(56.dp)
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Assessment Completed!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "You tested your knowledge on indications and contraindications.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }

                    // Score Card
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(0.8f)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "YOUR SCORE",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 1.5.sp
                            )
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "$score",
                                    style = MaterialTheme.typography.displayLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = " / ${questions.size}",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )
                            }

                            val scorePercentage = (score.toFloat() / questions.size) * 100
                            val message = when {
                                scorePercentage >= 100 -> "Flawless score! Yousif Mahmoud is proud of you!"
                                scorePercentage >= 80 -> "Excellent job! You have mastered these cardiovascular medications!"
                                scorePercentage >= 60 -> "Good work! Keep reviewing to achieve a perfect score!"
                                else -> "Keep studying! Review the lecture guidelines and try again."
                            }

                            Text(
                                text = message,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                currentIndex = 0
                                selectedOption = null
                                isAnswered = false
                                score = 0
                                quizCompleted = false
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Retry",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Retry Quiz", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
