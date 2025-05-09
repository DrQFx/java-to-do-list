package net.drqf.to_do_list.controllers;


import net.drqf.to_do_list.model.ToDoItem;
import net.drqf.to_do_list.repositories.ToDoItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ToDoListController implements CommandLineRunner {

    private final ToDoItemRepository toDoItemRepository;

    public ToDoListController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    @GetMapping
    public String Index(Model model) {

        List<ToDoItem> allTodos = toDoItemRepository.findAll();
        model.addAttribute("allTodos", allTodos);
        model.addAttribute("newToDo", new ToDoItem());

        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ToDoItem todoItem) {
        toDoItemRepository.save(todoItem);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        toDoItemRepository.deleteById(id);

        return "redirect:/";
    }

    @PostMapping("/deleteAll")
    public String deleteAllTodos() {
        toDoItemRepository.deleteAll();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchTodoItems(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<ToDoItem> allItems = toDoItemRepository.findAll();
        ArrayList<ToDoItem> searchResults = new ArrayList<>();

        for (ToDoItem item : allItems) {
            if (item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(item);
            }
        }

        model.addAttribute("allTodos", searchResults);
        model.addAttribute("newToDo", new ToDoItem());
        model.addAttribute("searchTerm", searchTerm);

        return "index";
    }


    @Override
    public void run(String... args) throws Exception {
        toDoItemRepository.save(new ToDoItem("Example To-Do 0"));
        toDoItemRepository.save(new ToDoItem("Example To-Do 1"));
    }
}
