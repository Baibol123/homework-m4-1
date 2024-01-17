package com.example.homework_m4_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.homework_m4_1.ListAdapter
import com.example.homework_m4_1.ListTasks
import com.example.homework_m4_1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val tasks = ArrayList<ListTasks>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (tasks.isEmpty()) loadData(tasks)
        binding.taskList.adapter = ListAdapter(tasks, this::onItemClick)
        initListeners()
    }

    private fun initListeners() {
        val newTaskButton = binding.addButton
        newTaskButton.setOnClickListener {
            navigateToNewTaskFragment("Example text to pass")
        }

        setFragmentResultListener(TaskEditFragment.KEY1) { _, bundle ->
            val resultFragment = HomeFragmentArgs.fromBundle(bundle)
            if (resultFragment.argtext.isNotEmpty()) {
                handleTaskResult(resultFragment)
            }
        }
    }
    private fun navigateToNewTaskFragment(textToPass: String) {
        val action = HomeFragmentDirections.actionNavigationHomeToTaskEditFragment(
            argtext = textToPass
        )
        findNavController().navigate(action)
    }

    private fun handleTaskResult(resultFragment: HomeFragmentArgs) {
        if (resultFragment.addtask) {
            addNewTask(resultFragment)
        } else {
            updateExistingTask(resultFragment)
        }
    }

    private fun addNewTask(resultFragment: HomeFragmentArgs) {
        val newTask = ListTasks(
            name = resultFragment.argtext,
            position = tasks.size
        )
        tasks.add(newTask)
    }

    private fun updateExistingTask(resultFragment: HomeFragmentArgs) {
        val taskUpdate = tasks[resultFragment.position]
        taskUpdate.name = resultFragment.argtext
        binding.taskList.adapter?.notifyItemChanged(resultFragment.position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(position: Int) {
        val taskchoosen = tasks[position]
        val action = HomeFragmentDirections.actionNavigationHomeToTaskEditFragment(
            argtext = taskchoosen.name,
            position = position,
            addtask = false
        )
        findNavController().navigate(action)
    }
    private fun loadData(tasks: ArrayList<ListTasks>): ArrayList<ListTasks> {
        tasks.apply {
            add(ListTasks("Установить ясные и конкретные цели на ближайший месяц.", position = size))
            add(ListTasks("Создать еженедельный график физической активности и придерживайся его.", position = size))
            add(ListTasks("Прочитать одну книгу по личностному развитию в этом месяце.", position = size))
            add(ListTasks("Составить бюджет на следующие три месяца и придерживаться его.", position = size))
            add(ListTasks("Проведи вечер, посвященный общению с семьей или близкими друзьями.", position = size))
            add(ListTasks("Выбрось или подари вещи, которые тебе больше не нужны.", position = size))
            add(ListTasks("Определись с приоритетами в жизни и проверь, насколько твои текущие действия соответствуют этим приоритетам.", position = size))
            add(ListTasks("Изучить новый навык или язык в течение этого месяца.", position = size))

        }
        return tasks
    }

}