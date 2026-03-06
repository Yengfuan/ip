# Bella User Guide

Welcome to Bella! Bella is a personal task manager that runs in your terminal. You can add todos, deadlines, and events, mark them as done, and search through them.

---

## Quick Start

1. Ensure you have **Java 17** installed.
2. Download the latest `ip.jar` from the [releases page](#).
3. Open a terminal in the folder containing `ip.jar` and run:
   ```
   java -jar ip.jar
   ```
4. Type a command and press **Enter** to get started!

---

## Command Summary

| Command | Format |
|---|---|
| Add todo | `todo <description>` |
| Add deadline | `deadline <description> /by <yyyy-MM-dd HHmm>` |
| Add event | `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>` |
| List all tasks | `list` |
| Mark as done | `mark <index>` |
| Mark as not done | `unmark <index>` |
| Delete task | `delete <index>` |
| Find tasks | `find <keyword>` |
| Exit | `bye` |

---

## Features

### Adding a todo: `todo`

Adds a task with no deadline.

**Format:** `todo <description>`

**Example:**
```
todo read book
```
```
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
```

---

### Adding a deadline: `deadline`

Adds a task with a due date and time.

**Format:** `deadline <description> /by <yyyy-MM-dd HHmm>`

**Example:**
```
deadline return book /by 2026-03-10 1800
```
```
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Mar 10 2026 06:00pm)
 Now you have 2 tasks in the list.
____________________________________________________________
```

---

### Adding an event: `event`

Adds a task with a start and end date-time.

**Format:** `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

**Example:**
```
event project meeting /from 2026-03-05 1400 /to 2026-03-05 1600
```
```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Mar 05 2026 02:00pm to: Mar 05 2026 04:00pm)
 Now you have 3 tasks in the list.
____________________________________________________________
```

---

### Listing all tasks: `list`

Shows all tasks currently in your list.

**Format:** `list`

**Example:**
```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] return book (by: Mar 10 2026 06:00pm)
 3.[E][ ] project meeting (from: Mar 05 2026 02:00pm to: Mar 05 2026 04:00pm)
____________________________________________________________
```

> Task icons: `[T]` = Todo, `[D]` = Deadline, `[E]` = Event
> Status icons: `[ ]` = not done, `[X]` = done

---

### Marking a task as done: `mark`

Marks the task at the given index as done.

**Format:** `mark <index>`

**Example:**
```
mark 1
```
```
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read book
____________________________________________________________
```

---

### Marking a task as not done: `unmark`

Marks the task at the given index as not done.

**Format:** `unmark <index>`

**Example:**
```
unmark 1
```
```
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] read book
____________________________________________________________
```

---

### Deleting a task: `delete`

Removes the task at the given index from the list.

**Format:** `delete <index>`

**Example:**
```
delete 2
```
```
____________________________________________________________
 OK, I've removed this task:
   [D][ ] return book (by: Mar 10 2026 06:00pm)
 Now you have 2 tasks in the list.
____________________________________________________________
```

---

### Finding tasks by keyword: `find`

Searches for tasks whose descriptions contain the given keyword. Case-insensitive.

**Format:** `find <keyword>`

**Example:**
```
find book
```
```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] return book (by: Mar 10 2026 06:00pm)
____________________________________________________________
```

---

### Exiting: `bye`

Saves your tasks and exits Bella.

**Format:** `bye`

---

## Data Storage

Bella automatically saves your tasks to `./data/bella.txt` after every command. Your tasks are loaded back when you restart Bella.
> ⚠️ Do not manually edit `bella.txt`!
