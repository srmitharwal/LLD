package PC;

import java.util.LinkedList;

public class PC {
    LinkedList<Integer> list = new LinkedList<>();
    private int size;

    public PC(int size) {
        this.size = size;
    }

    public void produce(int data) throws InterruptedException {

        synchronized (this) {
            while (list.size() == size) {
                //System.out.println("size if full");
                wait();
            }
            System.out.println("produced : {} " + data);
            list.add(data);
            notify();
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {

        synchronized (this) {
            while (list.size() == 0){
                //System.out.println("size if zero");
                wait();
            }

            System.out.println("consumed : {} " + list.removeFirst());

            notify();
            Thread.sleep(1000);
        }
    }
}
