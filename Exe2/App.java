package Exe2;

interface App {
    public void add(Object obj) throws Exception;

    public void run();

    public void printAll();

    default public void exit() {
        System.out.println("Exiting App...\n");
    }
}
