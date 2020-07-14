// 实训第二天作业 第一大题

import java.util.HashSet;
import java.util.Iterator;

public class Homework1 {
    /**
     * 定义图书类Book，具有属性账号id，书名name、作者author 和价格price，在创建图书对象时要求通过构造器进行创建，
     * 一次性将四个属性全部赋值，要求账户属性是int型，名称是String型，作者是String型，价格是double,请合理进行封装。
     */
    public static void main(String[] args) {

    }
}

class Book {
    private int id;
    private String name;
    private String author;
    private double price;

    public Book(int id, String name, String author, double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        /* 1)在Book类，添加toString方法，要求返回 图书信息字符串，使用\t隔开各信息 */
        return this.id + "\t" + this.name + "\t" + this.author + "\t"
                + this.price;
    }

    @Override
    public boolean equals(Object object) {
        /* 5）不允许添加重复的图书（如果账号id和书名name相同，则认为两本书是相同的） */
        // 重写equals函数，然后利用HashSet的特性来实现去重
        if(object instanceof Book) {
            Book book = (Book)object;
            if(this.id == book.getId() && this.name.equals(book.getName())) {
                return true;
            }
        }
        return false;
    }
}

class Library {
    HashSet<Book> books;

    public Library() {
        books = new HashSet<>();    /* 2) 要求定义一个图书馆Library类，在图书馆类中添加一个HashSet集合用于保存多本图书 */
    }

    public void add(Book book) {
        books.add(book);    /* 3）在图书馆类中要求能够新增图书 */
    }

    public void show() {
        /* 4）在图书馆类中要求可以查看所有添加过的图书 */
        //遍历HashSet
        Iterator iterator = books.iterator();
        while(iterator.hasNext()) {
            Book book = (Book)iterator.next();
            System.out.println(book.toString());
        }
    }

    public boolean delete(int id) {
        /* 6）可以根据id删除图书 */
        Iterator iterator = books.iterator();
        while(iterator.hasNext()) {
            Book book = (Book)iterator.next();
            if(book.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


}