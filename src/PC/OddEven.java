package PC;

public class OddEven {

    private boolean printEven;

    public OddEven(){
        this.printEven = true;
    }

    public void printEvenNo() throws InterruptedException {
        int x = 0;
        while(true) {
            synchronized (this) {
                while(!printEven) {
                    wait();
                }

                System.out.println(x);
                System.out.println(x+2);
                x += 4;
                printEven = false;
                notify();
                Thread.sleep(100);
            }
        }
    }


    public void printOddNo() throws InterruptedException {
        int x = 1;
        while (true) {
            synchronized (this) {
                while (printEven) {
                    wait();
                }

                System.out.println(x);
                System.out.println(x+2);
                x += 4;
                printEven = true;
                notify();
                Thread.sleep(100);
            }
        }
    }

}





