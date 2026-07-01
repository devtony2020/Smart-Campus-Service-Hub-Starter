# Pseudocode and Algorithms

## Balanced delimiter checker using a stack
```
CREATE empty stack
FOR each character ch in command
    IF ch is opening delimiter
        PUSH ch
    ELSE IF ch is closing delimiter
        IF stack is empty RETURN false
        open = POP stack
        IF open and ch do not match RETURN false
RETURN stack is empty
```

## Undo/redo using two stacks
```
update(newStatus):
    PUSH currentStatus onto undoStack
    currentStatus = newStatus
    CLEAR redoStack

undo():
    IF undoStack is empty RETURN currentStatus
    PUSH currentStatus onto redoStack
    currentStatus = POP undoStack
    RETURN currentStatus

redo():
    IF redoStack is empty RETURN currentStatus
    PUSH currentStatus onto undoStack
    currentStatus = POP redoStack
    RETURN currentStatus
```

## Circular queue
```
enqueue(x):
    IF size == capacity RETURN false
    data[rear] = x
    rear = (rear + 1) mod capacity
    size = size + 1
    RETURN true

dequeue():
    IF size == 0 RETURN null
    item = data[front]
    data[front] = null
    front = (front + 1) mod capacity
    size = size - 1
    RETURN item
```

## Priority queue comparator
```
If ticket A has higher severity than ticket B, A comes first.
If both have equal severity, the ticket with earlier arrivalOrder comes first.
```
