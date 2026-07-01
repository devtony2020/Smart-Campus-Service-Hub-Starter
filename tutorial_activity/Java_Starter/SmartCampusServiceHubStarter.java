import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * DCIT 308 Tutorial Activity: Smart Campus Service Hub
 *
 * WHAT STUDENTS ARE DOING:
 * You are completing a small simulation for a university service hub.
 * Each TODO corresponds to one concept from Lectures 2 and 3:
 * TODO 1: Stack application - balanced delimiter validation
 * TODO 2: Two stacks - undo/redo status updates
 * TODO 3: Circular queue - fixed-size ICT lab waiting line
 * TODO 4: Deque - shuttle boarding from front and back
 * TODO 5: Priority queue - urgent tickets served first
 *
 * RULE:
 * Complete only the TODO sections. Do not rename classes or method signatures.
 */
public class SmartCampusServiceHubStarter {

    // =========================================================
    // TODO 1: STACK APPLICATION - BALANCED DELIMITER CHECKER
    // =========================================================
    // Real-life meaning:
    // A service kiosk receives commands such as PRINT[STUDENT(11345217)].
    // Before processing a command, the system checks whether brackets match.
    //
    // Your task:
    // Return true if (), [] and {} are correctly balanced.
    // Return false if any closing delimiter has no matching opening delimiter.
    // Return false if any opening delimiter remains unmatched at the end.
    public static boolean isBalancedCommand(String command) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < command.length(); i++) {
            char ch = command.charAt(i);

            // TODO 1A: If ch is an opening delimiter '(', '[' or '{', push it.
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // TODO 1B: If ch is a closing delimiter ')', ']' or '}',
            // check whether the stack is empty.
            // If empty, return false.
            // Otherwise pop the top and check whether it matches ch.
            // If it does not match, return false.
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
        }

        // TODO 1C: Return true only when the stack is empty.
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    // =========================================================
    // TODO 2: TWO STACKS - UNDO/REDO STATUS UPDATES
    // =========================================================
    // Real-life meaning:
    // A helpdesk officer may update a ticket status wrongly and must undo it.
    public static class TicketStatusHistory {
        private Stack<String> undoStack = new Stack<>();
        private Stack<String> redoStack = new Stack<>();
        private String currentStatus;

        public TicketStatusHistory(String initialStatus) {
            currentStatus = initialStatus;
        }

        public void updateStatus(String newStatus) {
            // TODO 2A: push currentStatus onto undoStack.
            // TODO 2B: set currentStatus to newStatus.
            // TODO 2C: clear redoStack because a new edit path has started.
            undoStack.push(currentStatus);
            currentStatus = newStatus;
            redoStack.clear();
        }

        public String undo() {
            // TODO 2D: if undoStack is empty, return currentStatus.
            // TODO 2E: push currentStatus onto redoStack.
            // TODO 2F: pop from undoStack and make it the currentStatus.
            // TODO 2G: return currentStatus.
            if (undoStack.isEmpty()) {
                return currentStatus;
            }
            redoStack.push(currentStatus);
            currentStatus = undoStack.pop();
            return currentStatus;
        }

        public String redo() {
            // TODO 2H: if redoStack is empty, return currentStatus.
            // TODO 2I: push currentStatus onto undoStack.
            // TODO 2J: pop from redoStack and make it the currentStatus.
            // TODO 2K: return currentStatus.
            if (redoStack.isEmpty()) {
                return currentStatus;
            }
            undoStack.push(currentStatus);
            currentStatus = redoStack.pop();
            return currentStatus;
        }

        public String getCurrentStatus() {
            return currentStatus;
        }
    }

    // =========================================================
    // TODO 3: CIRCULAR QUEUE - FIXED-SIZE WAITING LINE
    // =========================================================
    // Real-life meaning:
    // The ICT lab has limited waiting slots. When rear reaches the end of
    // the array, it wraps around using modulo arithmetic.
    public static class CircularWaitingQueue {
        private String[] data;
        private int front = 0;
        private int rear = 0; // rear points to the next insertion position
        private int size = 0;

        public CircularWaitingQueue(int capacity) {
            data = new String[capacity];
        }

        public boolean isEmpty() {
            // TODO 3A: return true when size is 0.
            return size == 0;
        }

        public boolean isFull() {
            // TODO 3B: return true when size equals data.length.
            return size == data.length;
        }

        public boolean enqueue(String studentName) {
            // TODO 3C: if full, return false.
            // TODO 3D: store studentName at data[rear].
            // TODO 3E: update rear using (rear + 1) % data.length.
            // TODO 3F: increase size and return true.
            if (isFull()) {
                return false;
            }
            data[rear] = studentName;
            rear = (rear + 1) % data.length;
            size++;
            return true;
        }

        public String dequeue() {
            // TODO 3G: if empty, return null.
            // TODO 3H: store data[front] in a temporary variable.
            // TODO 3I: set data[front] to null.
            // TODO 3J: update front using (front + 1) % data.length.
            // TODO 3K: decrease size and return the removed value.
            if (isEmpty()) {
                return null;
            }
            String removedValue = data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;
            return removedValue;
        }

        public List<String> snapshotInQueueOrder() {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int index = (front + i) % data.length;
                result.add(data[index]);
            }
            return result;
        }
    }

    // =========================================================
    // TODO 4: DEQUE - SHUTTLE BOARDING FROM BOTH ENDS
    // =========================================================
    // Real-life meaning:
    // Regular passengers join at the back. A passenger with urgent medical
    // access may be placed at the front. Boarding can happen from either end.
    public static class ShuttleDeque {
        private Deque<String> passengers = new ArrayDeque<>();

        public void addRegularPassenger(String name) {
            // TODO 4A: add a regular passenger at the back of the deque.
            passengers.addLast(name);
        }

        public void addPriorityPassenger(String name) {
            // TODO 4B: add a priority passenger at the front of the deque.
            passengers.addFirst(name);
        }

        public String boardFromFront() {
            // TODO 4C: remove and return the passenger at the front.
            // Return null if no passenger exists.
            return passengers.pollFirst();
        }

        public String boardFromBack() {
            // TODO 4D: remove and return the passenger at the back.
            // Return null if no passenger exists.
            return passengers.pollLast();
        }

        public List<String> snapshot() {
            return new ArrayList<>(passengers);
        }
    }

    // =========================================================
    // TODO 5: PRIORITY QUEUE - URGENT TICKETS FIRST
    // =========================================================
    public static class Ticket {
        String id;
        String description;
        int severity; // 5 = most urgent, 1 = least urgent
        int arrivalOrder; // smaller number means earlier arrival

        public Ticket(String id, String description, int severity, int arrivalOrder) {
            this.id = id;
            this.description = description;
            this.severity = severity;
            this.arrivalOrder = arrivalOrder;
        }

        @Override
        public String toString() {
            return id + "(severity=" + severity + ", arrival=" + arrivalOrder + ")";
        }
    }

    public static Queue<Ticket> createTicketPriorityQueue() {
        // TODO 5A: Return a PriorityQueue<Ticket> that serves:
        // 1. Higher severity first.
        // 2. If severity is equal, earlier arrivalOrder first.
        return new PriorityQueue<>((t1, t2) -> {
            if (t1.severity != t2.severity) {
                return Integer.compare(t2.severity, t1.severity);
            }
            return Integer.compare(t1.arrivalOrder, t2.arrivalOrder);
        });
    }

    // =========================================================
    // MAIN METHOD: use this after completing the TODOs.
    // Expected output is given in the worksheet.
    // =========================================================
    public static void main(String[] args) {
        System.out.println("=== TODO 1: Balanced Command Checker ===");
        System.out.println(isBalancedCommand("PRINT[STUDENT(11345217)]")); // expected true
        System.out.println(isBalancedCommand("PRINT[STUDENT(11345217]")); // expected false

        System.out.println("\n=== TODO 2: Ticket Status Undo/Redo ===");
        TicketStatusHistory history = new TicketStatusHistory("Submitted");
        history.updateStatus("Reviewed");
        history.updateStatus("Assigned");
        history.updateStatus("In Progress");
        System.out.println(history.undo()); // expected Assigned
        System.out.println(history.undo()); // expected Reviewed
        System.out.println(history.redo()); // expected Assigned

        System.out.println("\n=== TODO 3: Circular Waiting Queue ===");
        CircularWaitingQueue waiting = new CircularWaitingQueue(4);
        waiting.enqueue("Amina");
        waiting.enqueue("Kojo");
        waiting.enqueue("Efua");
        System.out.println(waiting.dequeue()); // expected Amina
        waiting.enqueue("Yaw");
        waiting.enqueue("Adjoa");
        System.out.println(waiting.snapshotInQueueOrder()); // expected [Kojo, Efua, Yaw, Adjoa]
        System.out.println(waiting.enqueue("Mansa")); // expected false because queue is full

        System.out.println("\n=== TODO 4: Shuttle Deque ===");
        ShuttleDeque shuttle = new ShuttleDeque();
        shuttle.addRegularPassenger("Kojo");
        shuttle.addRegularPassenger("Ama");
        shuttle.addPriorityPassenger("Nurse Efua");
        System.out.println(shuttle.snapshot()); // expected [Nurse Efua, Kojo, Ama]
        System.out.println(shuttle.boardFromFront()); // expected Nurse Efua
        System.out.println(shuttle.boardFromBack()); // expected Ama

        System.out.println("\n=== TODO 5: Priority Ticket Queue ===");
        Queue<Ticket> tickets = createTicketPriorityQueue();
        tickets.add(new Ticket("T1", "Network outage", 5, 1));
        tickets.add(new Ticket("T2", "Password reset", 2, 2));
        tickets.add(new Ticket("T3", "Projector failure", 4, 3));
        tickets.add(new Ticket("T4", "Lab PC not starting", 4, 4));
        while (!tickets.isEmpty()) {
            System.out.println(tickets.poll());
        }
        // expected: T1 first, then T3, then T4, then T2.
    }
}
