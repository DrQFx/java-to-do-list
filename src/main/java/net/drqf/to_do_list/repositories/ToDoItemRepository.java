package net.drqf.to_do_list.repositories;

import net.drqf.to_do_list.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

}
