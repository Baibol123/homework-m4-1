package com.example.homework_m4_1.ui.home.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.homework_m4_1.ListAdapter
import com.example.homework_m4_1.ListTasks
import com.example.homework_m4_1.R
import com.example.homework_m4_1.TypeTasks
import com.example.homework_m4_1.ViewPagerAdapter
import com.example.homework_m4_1.databinding.FragmentHomeBinding
import com.example.homework_m4_1.ui.home.TaskEditFragment
import com.example.homework_m4_1.ui.home.TaskEditFragmentArgs
import com.example.homework_m4_1.ui.tasks.viewpager.AllListFragment
import com.example.homework_m4_1.ui.tasks.viewpager.ComplitedFragment
import com.example.homework_m4_1.ui.tasks.viewpager.ProgressFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val tasks = ArrayList<ListTasks>()
    private val binding get() = _binding!!
    private val AllListTasks = loadData(ArrayList())
    private val HomeTasks = filterTasks(TypeTasks.LIST)
    private val ProgressTasks = filterTasks(TypeTasks.IN_PROGRESS)
    private val complitedTasks = filterTasks(TypeTasks.COMPLETED)

    private val arrayFragments = listOf(
        AllListFragment(AllListTasks, this::onItemClick),
        ProgressFragment(ProgressTasks, this::onItemClick),
        ComplitedFragment(complitedTasks, this::onItemClick)
    )
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
        setupViewPagerAndTabs()
        initListeners()
    }

    private fun filterTasks(taskType: TypeTasks): ArrayList<ListTasks> =
        ArrayList(AllListTasks.filter { it.type == taskType })

    private fun setupViewPagerAndTabs() {
        binding.tasksVp.adapter =
            ViewPagerAdapter(childFragmentManager, lifecycle, arrayFragments)
        binding.tasksVp.isUserInputEnabled = false
        TabLayoutMediator(binding.tabBar, binding.tasksVp) { tab, position ->
            tab.text = when (position) {
                0 -> resources.getString(R.string.str_all_tasks)
                1 -> resources.getString(R.string.str_progress_tasks)
                2 -> resources.getString(R.string.str_completed_tasks)
                else -> resources.getString(R.string.str_completed_tasks)
            }
        }.attach()
    }

    private fun initListeners() {
        binding.addButton.setOnClickListener {
            navigateToNewTaskFragment(ListTasks(), true, TypeTasks.IN_PROGRESS)
        }

        setFragmentResultListener(TaskEditFragment.KEY1) { _, bundle ->
            val result = TaskEditFragmentArgs.fromBundle(bundle)
            updateTasks(result)
        }
    }

    private fun updateTasks(result: TaskEditFragmentArgs) {
        if (result.argtask.name.isNotEmpty()) {
            if (result.addtaskarg) {
                addTask(result.argtask)
            } else {
                updateExistingTask(result)
            }
        }
    }

    private fun addTask(argTask: ListTasks) {
        argTask.position = AllListTasks.size
        AllListTasks.add(argTask)
        when (argTask.type) {
            TypeTasks.LIST -> HomeTasks.add(argTask)
            TypeTasks.IN_PROGRESS -> ProgressTasks.add(argTask)
            TypeTasks.COMPLETED -> complitedTasks.add(argTask)
        }
    }
    private fun updateExistingTask(result: TaskEditFragmentArgs) {
        val existingTaskList = getTaskListByType(result.typearg)
        val existingTask = existingTaskList.find { it.position == result.argtask.position }

        existingTaskList.remove(existingTask)

        when (result.argtask.type) {
            TypeTasks.LIST -> HomeTasks.add(result.argtask)
            TypeTasks.IN_PROGRESS -> ProgressTasks.add(result.argtask)
            TypeTasks.COMPLETED -> complitedTasks.add(result.argtask)
        }
    }

    private fun getTaskListByType(taskType: TypeTasks): ArrayList<ListTasks> = when (taskType) {
        TypeTasks.LIST -> HomeTasks
        TypeTasks.IN_PROGRESS -> ProgressTasks
        TypeTasks.COMPLETED -> complitedTasks
    }

    private fun navigateToNewTaskFragment(argTask: ListTasks, addTask: Boolean, typearg: TypeTasks) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToTaskEditFragment(
                argTask, addTask, typearg
            )
        )
    }

    private fun onItemClick(position: Int) {
        navigateToNewTaskFragment(AllListTasks[position], addTask = false, typearg = AllListTasks[position].type)
    }
    private fun loadData(tasks: ArrayList<ListTasks>): ArrayList<ListTasks> {
        tasks.apply {
            add(ListTasks("Установить ясные и конкретные цели на ближайший месяц.", position = size, type = TypeTasks.LIST))
            add(ListTasks("Создать еженедельный график физической активности и придерживайся его.", position = size,type = TypeTasks.LIST))
            add(ListTasks("Прочитать одну книгу по личностному развитию в этом месяце.", position = size,type = TypeTasks.LIST))
            add(ListTasks("Составить бюджет на следующие три месяца и придерживаться его.", position = size,type = TypeTasks.LIST))
            add(ListTasks("Проведи вечер, посвященный общению с семьей или близкими друзьями.", position = size,type = TypeTasks.LIST))
            add(ListTasks("Выбрось или подари вещи, которые тебе больше не нужны.", position = size,type = TypeTasks.LIST))
            add(ListTasks("Определись с приоритетами в жизни и проверь, насколько твои текущие действия соответствуют этим приоритетам.", position = size,type = TypeTasks.LIST))
            add(ListTasks("Изучить новый навык или язык в течение этого месяца.", position = size,type = TypeTasks.LIST))

        }
        return tasks
    }

}