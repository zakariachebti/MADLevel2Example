package com.example.madlevel2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2example.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        // Create an onClickListener associated with the button
        binding.btnAddReminder.setOnClickListener {
            val reminder = binding.etReminder.text.toString()
            addReminder(reminder)
        }

        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvReminders.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvReminders.adapter = reminderAdapter
        binding.rvReminders.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

    }

    // addReminder method
    private fun addReminder(reminder: String) {
        if (reminder.isNotBlank()) {
            reminders.add(Reminder(reminder))
            reminderAdapter.notifyDataSetChanged()
            binding.etReminder.text?.clear()
        } else {
            Snackbar.make(binding.etReminder, "You must fill in the input field!", Snackbar.LENGTH_SHORT).show()
        }
    }

}