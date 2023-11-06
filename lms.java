import java.util.*;
class admin
{
    String email,pass;
    ArrayList<book>list=new ArrayList<>();
    admin(String email,String pass)
    {
        this.email=email;
        this.pass=pass;
    }
}
class guest
{
    String email,pass;
    int amount;
    HashMap<String,book>borrow=new HashMap<>();
    guest(String email,String pass,int amount)
    {
        this.email=email;
        this.pass=pass;
        this.amount=amount;
    }
    void display()
    {
        System.out.println("email:"+this.email);
        System.out.println("Remaining amount:"+this.amount);
        System.out.println("Borrowed books");
        for(String b:borrow.keySet())
        {
            borrow.get(b).display();
        }
    }
}
class book
{
    String name;
    int avail,price;
    String un;
    book(String name,int avail,String un,int price)
    {
        this.name=name;
        this.avail=avail;
        this.un=un;
        this.price=price;
    }
    void display()
    {
        System.out.println("------------------------------");
        System.out.println("Book name:  "+this.name);
        System.out.println("Book availability:  "+this.avail);
        System.out.println("Book Unique number:  "+this.un);
        System.out.println("Book Price:  "+this.price);
        System.out.println("------------------------------");
    }
}
public class lms
{
    static HashMap<String,book>book_map=new HashMap<>();
    static HashMap<String,admin>admin_map=new HashMap<>();
    static HashMap<String,guest>guest_map=new HashMap<>();
    public static void admin()
    {
        System.out.println("Enter your mail:");
        String input=in.nextLine();
        System.out.println("Enter your pass:");
        String pass=in.nextLine();
        if(admin_map.containsKey(input))
        {
            if(admin_map.get(input).pass.equals(pass))admin_fun();
            else System.out.println("Incorrect password");
        }
        else
        {
            System.out.println("No user found");
            return;
        }
    }
    public static void admin_fun()
    {
        boolean flag=true;
        while(flag)
        {
            System.out.println("1.add book\n2.view books\n3.modify book\n4.delete book\n5.add admin\n6.add guest\n7.search book\n8.manage fine\n9.logout");
            String input=in.nextLine();
            switch (input)
            {
                case "9":
                {
                    flag=false;
                    break;
                }
                case "1":
                {
                    System.out.println("Enter book name:");
                    String book_name=in.nextLine();
                    System.out.println("Enter number of books:");
                    int avail=Integer.parseInt(in.nextLine());
                    System.out.println("Enter unique number:");
                    String un=in.nextLine();
                    System.out.println("Enter the price:");
                    String price=in.nextLine();
                    book a=new book(book_name,avail,un,Integer.parseInt(price));
                    book_map.put(un,a);
                    break;
                }
                case "2":
                {
                    for(String s:book_map.keySet())book_map.get(s).display();
                    break;
                }
                case "3":
                {
                    System.out.println("Enter unique number");
                    String un=in.nextLine();
                    if(!book_map.containsKey(un))
                    {
                        System.out.println("no book found");
                        break;
                    }
                    book a=book_map.get(un);
                    System.out.println("1.modify name\n2.modify availability\n3.modify unique number");
                    String choice=in.nextLine();
                    if(choice.equals("1"))
                    {
                        System.out.println("Enter new name");
                        String new_name=in.nextLine();
                        a.name=new_name;
                        book_map.put(un,a);
                    }
                    if(choice.equals("2"))
                    {
                        System.out.println("Enter the quantity");
                        int num=Integer.parseInt(in.nextLine());
                        a.avail=num;
                        book_map.put(un,a);
                    }
                    if(choice.equals("3"))
                    {
                        System.out.println("Enter new unique number");
                        a.un=in.nextLine();
                        book_map.put(un,a);
                    }
                    break;
                }
                case "4":
                {
                    System.out.println("Enter unique number");
                    String rem=in.nextLine();
                    if(!book_map.containsKey(rem))
                    {
                        System.out.println("no such book exists");
                        break;
                    }
                    admin_map.remove(rem);
                    System.out.println("Book removed sucessfully");
                    break;
                }
                case "5":
                {
                    System.out.println("Enter admin mail:");
                    String mail=in.nextLine();
                    if(admin_map.containsKey(mail))
                    {
                        System.out.println("mail already exists");
                        break;
                    }
                    System.out.println("Enter password");
                    String pass=in.nextLine();
                    admin a=new admin(mail,pass);
                    admin_map.put(mail,a);
                    System.out.println("Account created Sucessfully");
                    break;
                }
                case "6":
                {
                    System.out.println("Enter guest mail:");
                    String mail=in.nextLine();
                    if(guest_map.containsKey(mail))
                    {
                     System.out.println("mail already exists");
                     break;
                    }
                    System.out.println("enter password:");
                    String pass=in.nextLine();
                    guest a=new guest(mail,pass,1500);
                    guest_map.put(mail,a);
                    System.out.println("Account created Sucessfully");
                    break;
                }
                case "7":
                {
                    System.out.println("Enter unique number");
                    String un=in.nextLine();
                    if(book_map.containsKey(un))
                    {
                        book_map.get(un).display();
                    }
                    else
                    {
                        System.out.println("Book not found");
                    }
                    break;
                }
            }
        }
    }
    public static void guest()
    {
        boolean flag=true;
        while(flag)
        {
            System.out.println("enter your mail:");
            String mail= in.nextLine();
            System.out.println("enter your password");
            String pass=in.nextLine();
            if(!guest_map.containsKey(mail))
            {
                System.out.println("no account exists");
                break;
            }
            if(guest_map.get(mail).pass.equals(pass))
            {
                flag=false;
                guest_fun(mail);
            }
            else
            {
                System.out.println("Incorrect password");
            }
        }
    }
    public static void return_book(String mail)
    {
            guest a=guest_map.get(mail);
            int index=1;
            String borrow_date="";
            for(String s:a.borrow.keySet())
            {
                System.out.print(index+":");
                a.borrow.get(s).display();
                index++;
            }
            System.out.println("enter the isn of book:");
            String input=in.nextLine();
            for(String s:a.borrow.keySet())
            {
                if(a.borrow.get(s).un.equals(input))borrow_date=s;
            }
            System.out.println("enter the return date:[dd/mm/yyyy]:");
            String return_date=in.nextLine();
            double fine=calculate_fine(mail,input,return_date);
            if(fine>book_map.get(input).price)fine=book_map.get(input).price*0.8;
            if(fine>0 && a.amount>fine)
            {
                a.amount-=fine;
                System.out.println("Fine deducted:"+fine);
            }
            else
            {
                if(fine>book_map.get(input).price)
                {
                    System.out.println("Insufficient balance\n please pay remaining amount");
                }
            }
//            System.out.println(a.borrow);
            a.borrow.remove(borrow_date);
//            System.out.println(a.borrow);
            guest_map.put(mail,a);
            book b=book_map.get(input);
            b.avail++;
            book_map.put(input,b);
            System.out.println("Book returned Sucessfully");
    }
    public static int calculate_fine(String mail,String isn,String return_date)
    {
        guest a=guest_map.get(mail);
        book b;
        String borrow_date="";
        int fine=0;
        for(String s:a.borrow.keySet())
        {
            if(a.borrow.get(s).un.equals(isn))
            {
                b=a.borrow.get(s);
                borrow_date=s;
            }
        }
        fine=fine_helper(borrow_date,return_date);
        return fine;
    }
    public static int fine_helper(String bd,String rd)
    {
        int days=0;
        int fine=0;
        String[] borrow=bd.split("/");
        String[] ret=rd.split("/");
        int year_rd=Integer.parseInt(ret[2]);
        int year_bd=Integer.parseInt(borrow[2]);
        days+=(year_rd-year_bd)*365;
        int month_bd=Integer.parseInt(borrow[1]);
        int month_rd=Integer.parseInt(ret[1]);
        days+=(month_rd-month_bd)*30;
        int return_day=Integer.parseInt(ret[0]);
        int borrow_day=Integer.parseInt(borrow[0]);
        days+=return_day-borrow_day;
        int rem=2;
        int counter=0;
        for(int i=15;i<days;i++)
        {
            fine+=rem;
            counter++;
            if(counter>10)
            {
                rem*=2;
                counter=0;
            }
        }
        return fine;
    }
    public static void guest_fun(String mail)
    {
        boolean flag=true;
        while(flag)
        {
            System.out.println("1.view book\n2.borrow book\n3.return book\n4.extend book\n5.my list\n6.logout");
            String input=in.nextLine();
            switch(input) {
                case "6": {
                    flag = false;
                    break;
                }
                case "5":
                {
                    guest g=guest_map.get(mail);
                    g.display();
                    break;
                }
                case "1":
                {
                    for(String s:book_map.keySet())book_map.get(s).display();
                    break;
                }
                case "2":
                {
                    System.out.println("Enter book isn");
                    String num=in.nextLine();
                    if(!book_map.containsKey(num))
                    {
                        System.out.println("No such book exists");
                        break;
                    }
                    book a=book_map.get(num);
                    if(a.avail>0)
                    {
                        a.avail--;
                        guest p=guest_map.get(mail);
                        if(p.borrow.size()>3)
                        {
                            System.out.println("You have borrowed max number of books! return any book to borrow");
                            System.out.println("y/n:");
                            String temp=in.nextLine();
                            if(temp.equals("y"))return_book(mail);
                            else break;
                        }
                        System.out.println("Enter the borrow date:[dd/mm/yyyy]");
                        String borrow_date=in.nextLine();
                        p.borrow.put(borrow_date,a);
                        guest_map.put(mail,p);
                        book_map.put(a.un,a);
                        System.out.println("---------------\nbook borrowed sucessfully\n-----------------");
                    }
                    else
                    {
                        System.out.println("That book is not available at present");
                    }
                    break;
                }
                case "3":
                {
                    return_book(mail);
                }
            }
        }
    }
    static Scanner in=new Scanner(System.in);
    public static void main(String[] args)
    {
        admin a=new admin("abc","1234");
        admin_map.put("abc",a);
        guest g=new guest("babu","123",1500);
        guest_map.put("babu",g);
        book b=new book("machine learning",10,"ab12",100);
        book_map.put("ab12",b);
        while(true)
        {
            System.out.println("---------------\n1.admin\n2.guest\n---------------");
            String input=in.nextLine();
            switch(input)
            {
                case "1":
                {
                    admin();
                    break;
                }
                case "2":
                {
                    guest();
                    break;
                }
                default:System.out.println("Invalid command");
            }
        }
    }
}
