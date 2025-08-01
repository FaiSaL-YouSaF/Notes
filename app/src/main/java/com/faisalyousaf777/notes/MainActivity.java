package com.faisalyousaf777.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.faisalyousaf777.notes.commons.entity.Note;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.toolbar_main);
        TabLayout tabLayout = findViewById(R.id.tab_layout_categories);
        ViewPager2 viewPager = findViewById(R.id.view_pager_notes);
        FloatingActionButton fabAddNote = findViewById(R.id.fab_add_note);

        Toolbar.OnMenuItemClickListener menuItemClickListener = item -> {
            if (item.getItemId() == R.id.action_edit) {
                Toast.makeText(this, "Action Edit", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.action_search) {
                Toast.makeText(this, "Action Search", Toast.LENGTH_SHORT).show();
            } else {
                return false;
            }
            return true;
        };
        toolbar.setOnMenuItemClickListener(menuItemClickListener);

        List<String> categories = List.of("All", "Work", "Personal", "Shopping", "Health", "Travel", "Finance");


        // Set up ViewPager2 with a FragmentStateAdapter
        RecyclerViewPagerAdapter adapter = new RecyclerViewPagerAdapter(
                this,
                categories,
                getNotesByCategory()
                );
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(categories.get(position));
        }).attach();

    }

    public Map<String, List<Note>> getNotesByCategory() {
        return new HashMap<String, List<Note>>() {{
            put("Work", List.of(
                    new Note("Work Meeting", "Discuss project status", "Work"),
                    new Note("Project Deadline", "Submit by end of week", "Work"),
                    new Note("Team Lunch", "Schedule with team", "Work"),
                    new Note("Client Call", "Prepare presentation", "Work"),
                    new Note("Code Review", "Review teammate's code", "Work"),
                    new Note("Send Report", "Email weekly report", "Work"),
                    new Note("Update Documentation", "Add new API details", "Work"),
                    new Note("Interview Candidate", "Prepare questions", "Work"),
                    new Note("Team Standup", "Daily sync", "Work"),
                    new Note("Fix Bugs", "Resolve open issues", "Work"),
                    new Note("Plan Sprint", "Organize tasks", "Work"),
                    new Note("Check Emails", "Respond to clients", "Work")
            ));
            put("Personal", List.of(
                    new Note("Personal Diary", "Write about today", "Personal"),
                    new Note("Read Book", "Finish current chapter", "Personal"),
                    new Note("Call Family", "Catch up with parents", "Personal"),
                    new Note("Watch Movie", "Pick a new release", "Personal"),
                    new Note("Go for Walk", "Evening stroll", "Personal"),
                    new Note("Cook Dinner", "Try new recipe", "Personal"),
                    new Note("Meditate", "10 minutes session", "Personal"),
                    new Note("Listen to Music", "Relaxing playlist", "Personal"),
                    new Note("Visit Friend", "Coffee meetup", "Personal"),
                    new Note("Organize Photos", "Sort recent pictures", "Personal"),
                    new Note("Write Poem", "Express thoughts", "Personal"),
                    new Note("Plan Weekend", "List activities", "Personal")
            ));
            put("Shopping", List.of(
                    new Note("Buy Groceries", "Milk, Bread, Eggs", "Shopping"),
                    new Note("Order Laptop", "Check online deals", "Shopping"),
                    new Note("Gift for Friend", "Birthday present", "Shopping"),
                    new Note("Buy Shoes", "Look for discounts", "Shopping"),
                    new Note("Get Vegetables", "Fresh produce", "Shopping"),
                    new Note("Purchase Books", "New releases", "Shopping"),
                    new Note("Buy Clothes", "Summer collection", "Shopping"),
                    new Note("Order Phone", "Compare models", "Shopping"),
                    new Note("Get Stationery", "Pens and notebooks", "Shopping"),
                    new Note("Buy Headphones", "Wireless options", "Shopping"),
                    new Note("Order Pizza", "Family dinner", "Shopping"),
                    new Note("Buy Flowers", "Anniversary gift", "Shopping")
            ));
            put("Health", List.of(
                    new Note("Doctor Appointment", "Annual checkup", "Health"),
                    new Note("Morning Run", "5km in the park", "Health"),
                    new Note("Take Vitamins", "Daily supplements", "Health"),
                    new Note("Yoga Session", "Stretching exercises", "Health"),
                    new Note("Drink Water", "Stay hydrated", "Health"),
                    new Note("Track Sleep", "Monitor hours", "Health"),
                    new Note("Eat Fruits", "Healthy snacks", "Health"),
                    new Note("Dental Checkup", "Routine visit", "Health"),
                    new Note("Meditation", "Mindfulness practice", "Health"),
                    new Note("Cycling", "Weekend ride", "Health"),
                    new Note("Cook Healthy Meal", "Low calorie", "Health"),
                    new Note("Check Blood Pressure", "Monitor regularly", "Health")
            ));
            put("Travel", List.of(
                    new Note("Trip to Paris", "Book flights and hotel", "Travel"),
                    new Note("Visa Application", "Submit documents", "Travel"),
                    new Note("Pack Bags", "Essentials for trip", "Travel"),
                    new Note("Travel Insurance", "Get coverage", "Travel"),
                    new Note("Book Taxi", "Airport transfer", "Travel"),
                    new Note("Check Weather", "Forecast for destination", "Travel"),
                    new Note("Make Itinerary", "Plan sightseeing", "Travel"),
                    new Note("Currency Exchange", "Get local money", "Travel"),
                    new Note("Buy Souvenirs", "Gift shopping", "Travel"),
                    new Note("Reserve Restaurant", "Dinner booking", "Travel"),
                    new Note("Update Passport", "Renew if needed", "Travel"),
                    new Note("Charge Devices", "Prepare electronics", "Travel")
            ));
            put("Finance", List.of(
                    new Note("Salary Received", "Check bank statement", "Finance"),
                    new Note("Pay Bills", "Electricity and Internet", "Finance"),
                    new Note("Invest Savings", "Review options", "Finance"),
                    new Note("Track Expenses", "Update spreadsheet", "Finance"),
                    new Note("Transfer Money", "Send to family", "Finance"),
                    new Note("Review Budget", "Monthly planning", "Finance"),
                    new Note("Tax Filing", "Prepare documents", "Finance"),
                    new Note("Renew Insurance", "Health and car", "Finance"),
                    new Note("Check Credit Score", "Monitor rating", "Finance"),
                    new Note("Buy Stocks", "Market research", "Finance"),
                    new Note("Withdraw Cash", "ATM visit", "Finance"),
                    new Note("Plan Retirement", "Long-term savings", "Finance")
            ));
        }};
    }
}