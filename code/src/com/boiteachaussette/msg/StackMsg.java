package com.boiteachaussette.msg;

public class StackMsg {
    private Msg msg;
    private StackMsg head;
    
    public StackMsg(Msg msg, StackMsg head){
        this.msg = msg;
        this.head = head;
    }
    
    public StackMsg(Msg msg){
        this.msg = msg;
    }

    public StackMsg reverse(){
        StackMsg tmpStack = null;
        StackMsg index = new StackMsg(this.msg, this.head);
        do {
            tmpStack= new StackMsg(index.getMsg(), tmpStack);
            index = index.getNext();
        }while (index!=null);
        return tmpStack;
    }
    public Msg pop(){
        Msg x = this.msg;
        if (this.head != null){
            this.del();
        } else {
            this.msg = null;
        }
        return x;
    }
    public void del(){
        this.msg = this.getNext().getMsg();
        this.head = this.getNext().getNext();
    }
    public void delMsg(){
        this.msg = null;
    }
    public boolean isEmpty(){
        return msg==null;
    }
    
    public void removeByFrom(String usr) {
        StackMsg current = this;
        StackMsg previous = null;
        while (current != null) {
            if (current.getMsg().getFrom().equals(usr)) {
                if (previous == null) {
                    this.msg = this.head.getMsg();
                    this.head = this.head.getNext();
                } else {
                    previous.head = current.head;
                }
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }
    public int getSize() {
        int size = 0;
        StackMsg current = this;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    public Msg getMsg(){
        return this.msg;
    }
    public StackMsg getNext(){
        return this.head;
    }
}
