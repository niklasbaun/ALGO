import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Comparison {


    /**
     * aufruf von HeapStack erzeugt Fehlermeldung
     * ich habe keine ahnung warum
     */
    public static void main(String[] args) {
        System.out.println("Comparison of HeapStack and util.Stack");
        //compare HeapStack and util.Stack
        HeapStack<Integer> heapStack = new HeapStack<>(10);
        Stack<Integer> stack = new Stack<>();


        //push 10 elements on both stacks and compare the time needed
        long startHeapPush = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            heapStack.push(i);
        }
        long endHeapPush = System.nanoTime();
        long heapPushTime = endHeapPush - startHeapPush;

        long startStackPush = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        long endStackPush = System.nanoTime();
        long stackPushTime = endStackPush - startStackPush;
        //print the time needed
        System.out.println("Time needed to push 10 elements on HeapStack: " + heapPushTime);
        System.out.println("Time needed to push 10 elements on Stack: " + stackPushTime);


        //peek at the top element of both stacks and compare the time needed
        long startHeapPeek = System.nanoTime();
        heapStack.peek();
        long endHeapPeek = System.nanoTime();
        long heapPeekTime = endHeapPeek - startHeapPeek;

        long startStackPeek = System.nanoTime();
        stack.peek();
        long endStackPeek = System.nanoTime();
        long stackPeekTime = endStackPeek - startStackPeek;

        //print the time needed
        System.out.println("Time needed to peek at the top element of HeapStack: " + heapPeekTime);
        System.out.println("Time needed to peek at the top element of Stack: " + stackPeekTime);

        //pop all elements of both stacks and compare the time needed
        long startHeapPop = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            heapStack.pop();
        }
        long endHeapPop = System.nanoTime();
        long heapPopTime = endHeapPop - startHeapPop;

        long startStackPop = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            stack.pop();
        }
        long endStackPop = System.nanoTime();
        long stackPopTime = endStackPop - startStackPop;

        //print the time needed
        System.out.println("Time needed to pop all elements of HeapStack: " + heapPopTime);
        System.out.println("Time needed to pop all elements of Stack: " + stackPopTime);

        System.out.println("End of Comparison");

        //compare HeapQueue and util.Queue
        System.out.println("Comparison of HeapQueue and util.ArrayDeque");
        HeapQueue<Integer> heapQueue = new HeapQueue<>(10);
        Queue<Integer> queue = new ArrayDeque<>();

        //add 10 elements to both queues and compare the time needed
        long startHeapAdd = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            heapQueue.add(i);
        }
        long endHeapAdd = System.nanoTime();
        long heapAddTime = endHeapAdd - startHeapAdd;

        long startQueueAdd = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        long endQueueAdd = System.nanoTime();
        long queueAddTime = endQueueAdd - startQueueAdd;

        //print the time needed
        System.out.println("Time needed to add 10 elements to HeapQueue: " + heapAddTime);
        System.out.println("Time needed to add 10 elements to Queue: " + queueAddTime);

        //peek at the first element of both queues and compare the time needed
        long startHeapPeekQueue = System.nanoTime();
        heapQueue.peek();
        long endHeapPeekQueue = System.nanoTime();
        long heapPeekQueueTime = endHeapPeekQueue - startHeapPeekQueue;

        long startQueuePeek = System.nanoTime();
        queue.peek();
        long endQueuePeek = System.nanoTime();
        long queuePeekTime = endQueuePeek - startQueuePeek;

        //print the time needed
        System.out.println("Time needed to peek at the first element of HeapQueue: " + heapPeekQueueTime);
        System.out.println("Time needed to peek at the first element of Queue: " + queuePeekTime);

        //remove all elements of both queues and compare the time needed
        long startHeapRemove = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            heapQueue.poll();
        }
        long endHeapRemove = System.nanoTime();
        long heapRemoveTime = endHeapRemove - startHeapRemove;

        long startQueueRemove = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            queue.poll();
        }
        long endQueueRemove = System.nanoTime();
        long queueRemoveTime = endQueueRemove - startQueueRemove;

        //print the time needed
        System.out.println("Time needed to remove all elements of HeapQueue: " + heapRemoveTime);
        System.out.println("Time needed to remove all elements of Queue: " + queueRemoveTime);
        System.out.println("End of Comparison");
    }
}
