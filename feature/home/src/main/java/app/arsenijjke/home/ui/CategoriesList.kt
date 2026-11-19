package app.arsenijjke.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.arsenijjke.home.viewmodel.CategoryUIModel
import coil3.compose.AsyncImage
import kotlin.math.absoluteValue

@Composable
internal fun CategoriesList(
    modifier: Modifier = Modifier,
    categories: List<CategoryUIModel>
) {
    Text(
        modifier = modifier,
        text = "Categories",
        style = MaterialTheme.typography.titleLarge,
        color = Color(45, 43, 42),
    )

    Spacer(modifier = Modifier.size(20.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(count = categories.size) { index ->
            CategoryItem(
                name = categories[index].name,
                imageUri = categories[index].imageUri,
            )
        }
    }
}

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    name: String,
    imageUri: String,
) {

    val backgroundColor = remember(name) { colorForKey(name) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(72.dp)
                .shadow(3.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(backgroundColor),

            ) {
            AsyncImage(
                modifier = Modifier.align(Alignment.Center),
                model = imageUri,
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.size(14.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

fun colorForKey(key: String): Color {
    val categoryCardColors = listOf(
        Color(0xFFFCEBE8),
        Color(0xFFE8ECE6),
        Color(0xFFE6F0F2),
        Color(0xFFFDF0E6),
        Color(0xFFF3EBF5),
        Color(0xFFFDE8EF),
        Color(0xFFEAF3E4),
        Color(0xFFF0EDF0),
        Color(0xFFE4EEF7),
        Color(0xFFF7E9DD),
    )

    val index = key.hashCode().absoluteValue % categoryCardColors.size
    return categoryCardColors[index]
}


@Preview(
    showBackground = true
)
@Composable
fun CategoryListPreview() {

    val mockedCategory = CategoryUIModel(
        id = "",
        name = "Beef",
        imageUri = "xddmks",
    )
    CategoriesList(
        categories = listOf(mockedCategory, mockedCategory)
    )
}

@Preview(
    showBackground = true
)
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        name = "Beef",
        imageUri = "",
    )
}
