package app.arsenijjke.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.arsenijjke.home.R
import app.arsenijjke.home.viewmodel.HomeViewModel
import app.arsenijjke.home.viewmodel.MealUiModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {
    val state: HomeViewModel.MealUiState by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeViewModel.MealUiState,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 48.dp)
    ) {

        when (state) {
            is HomeViewModel.MealUiState.Success -> {
                var expanded by remember { mutableStateOf(false) }
                val textFieldState = rememberTextFieldState("")
                HomeHeader(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    state
                )

                // TODO("Move to custom searchBar later")
                SimpleSearchBar(
                    textFieldState = TextFieldState("Null"),
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    onSearch = {},
                    searchResults = emptyList(),
                    modifier = Modifier
                        .requiredHeight(100.dp)
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                )

                MealOfTheDay(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    state = state
                )

                Spacer(modifier = Modifier.size(44.dp))

                CategoriesList(modifier = Modifier.padding(horizontal = 24.dp))
            }

            HomeViewModel.MealUiState.Error -> {
                TODO()
            }

            HomeViewModel.MealUiState.Loading -> {
                CircularProgressIndicator()
            }
        }

    }

}

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    state: HomeViewModel.MealUiState.Success
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Good " + state.meal.greeting + ",",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
            Text(
                text = state.meal.username,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Cyan)
        )
    }
}

@Composable
fun MealOfTheDay(
    modifier: Modifier = Modifier,
    state: HomeViewModel.MealUiState.Success
) {
    Column(
        modifier = modifier
    ) {
        MealHeader()
        MealCard(
            state = state
        )
    }
}

@Composable
private fun MealHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Recipe of the day",
            style = MaterialTheme.typography.titleLarge,
            color = Color(45, 43, 42),
        )
        Text(
            text = "View all",
            style = MaterialTheme.typography.labelLarge,
            color = Color(216, 89, 63),
        )
    }
}

@Composable
fun MealCard(
    modifier: Modifier = Modifier,
    state: HomeViewModel.MealUiState.Success
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(354.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.matchParentSize()
            )

            //TODO("Add Blur or smth Haze")
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                    contentDescription = null,
                )
            }

            MealCardContent(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, top = 24.dp),
                state = state,
            )

        }
    }
}

@Composable
fun TypeOfMealChip(
    modifier: Modifier = Modifier,
    typeOfMeal: String
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.Red),
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 6.dp),
            text = typeOfMeal,
            color = Color.White,
            fontSize = 10.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun TimeToCookChip(
    modifier: Modifier = Modifier,
    timeToCook: String
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.Black.copy(alpha = 0.2f)),
    ) {

        Row(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_access_time_24),
                tint = Color.White.copy(0.9f),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 6.dp)
                    .alpha(0.9f),
                text = timeToCook,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
            )
        }
    }
}

@Composable
fun MealCardContent(
    modifier: Modifier = Modifier,
    state: HomeViewModel.MealUiState.Success,
) {
    Column(
        modifier = modifier.padding(end = 30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            TypeOfMealChip(typeOfMeal = state.meal.typeOfMeal)
            TimeToCookChip(timeToCook = state.meal.timeToCook)
        }
        MealTitle(name = state.meal.mealName)
        MealDescription(description = state.meal.description)
    }
}

@Composable
private fun MealTitle(
    name: String
) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium,
        color = Color.White,
        maxLines = 2,
    )
}

@Composable
private fun MealDescription(
    description: String
) {
    Text(
        modifier = Modifier.alpha(0.8f),
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White,
        maxLines = 1,
    )
}

@Composable
private fun CategoriesList(
    modifier: Modifier = Modifier
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
        //TODO("Receive here: https://www.themealdb.com/api/json/v1/1/categories.php")
        items(count = 10) {
            CategoryItem()
        }
    }
}

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(72.dp)
                .shadow(3.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Cyan)

        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.size(14.dp))

        Text(
            text = "Beef",
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun HomeScreenPreview() {

    HomeScreen(
        state = HomeViewModel.MealUiState.Success(
            meal = MealUiModel(
                username = "UserName",
                mealName = "BRand new meal",
                description = "Kind of everything",
                typeOfMeal = "Dinner",
                isFavourite = false,
                categories = emptyList(),
                id = "123",
                timeToCook = "30 min",
                greeting = "Evening"
            )
        )
    )
}

@Preview(
    showBackground = true
)
@Composable
fun CategoryItemPreview() {
    CategoryItem()
}
