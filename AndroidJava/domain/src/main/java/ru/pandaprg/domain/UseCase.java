package ru.pandaprg.domain;

public  class UseCase implements DomainContract, InContract, InitializationContractIn {

    OutContract out;
    private static UseCase inctance;

    private int [] increment = {5,3,1};

    private int counter;

    private UseCase(OutContract out) {
        this.out = out;
        counter =0;
    }

    @Override
    public void in(int a) {
        counter += a;
       out.out(counter);
    }

    @Override
    public void getNumber() {
        out.out(counter);
    }

    public static UseCase getInstence (OutContract out) {
        if (inctance == null) {
            inctance = new UseCase(out);
        }
        return inctance;
    }

    @Override
    public void attach(OutContract out) {
        this.out = out;
        //out.out(counter);
    }

    @Override
    public void getIncrement(int n) {
        int i;

        if (n > 0 && n <= increment.length) {
            i = increment[n - 1];
        } else {
            i=0;
        }

        ((InitializationContractOut)out).onGetIncrement(n,i);
    }
}
