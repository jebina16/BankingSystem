import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private int totalCopies;
    private int availableCopies;

    public Book(int bookId, String title, String author, String category, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    public boolean issueBook() {
        if (availableCopies > 0) { availableCopies--; return true; }
        return false;
    }

    public void returnBook() {
        if (availableCopies < totalCopies) availableCopies++;
    }

    public String getStatus() {
        return availableCopies > 0 ? "Available (" + availableCopies + " left)" : "Not Available";
    }

    public void display() {
        System.out.printf("  %-5d %-30s %-20s %-15s %s%n",
                bookId, title, author, category, getStatus());
    }
}

// Member class
class Member {
    private int memberId;
    private String name;
    private String email;
    private ArrayList<Integer> issuedBookIds;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBookIds = new ArrayList<>();
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public ArrayList<Integer> getIssuedBookIds() { return issuedBookIds; }

    public boolean issueBook(int bookId) {
        if (issuedBookIds.size() >= 3) return false; // max 3 books
        issuedBookIds.add(bookId);
        return true;
    }

    public boolean returnBook(int bookId) {
        return issuedBookIds.remove(Integer.valueOf(bookId));
    }

    public void display() {
        System.out.printf("  %-6d %-20s %-25s %-10d%n",
                memberId, name, email, issuedBookIds.size());
    }
}

// Issue Record class
class IssueRecord {
    private int recordId;
    private int memberId;
    private String memberName;
    private int bookId;
    private String bookTitle;
    private String issueDate;
    private String status;

    public IssueRecord(int recordId, int memberId, String memberName,
                       int bookId, String bookTitle, String issueDate) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.issueDate = issueDate;
        this.status = "Issued";
    }

    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public String getStatus() { return status; }
    public void markReturned() { this.status = "Returned"; }

    public void display() {
        System.out.printf("  %-5d %-20s %-28s %-12s %-10s%n",
                recordId, memberName, bookTitle, issueDate, status);
    }
}

// Main Library Management System
public class LibraryManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();
    static ArrayList<IssueRecord> records = new ArrayList<>();
    static int nextBookId = 1;
    static int nextMemberId = 1;
    static int nextRecordId = 1;

    public static void main(String[] args) {
        printHeader();
        loadSampleData();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:  addBook();           break;
                case 2:  viewAllBooks();       break;
                case 3:  searchBook();         break;
                case 4:  addMember();          break;
                case 5:  viewAllMembers();     break;
                case 6:  issueBook();          break;
                case 7:  returnBook();         break;
                case 8:  viewIssueRecords();   break;
                case 9:  viewLibrarySummary(); break;
                case 10:
                    System.out.println("\n  Thank you for using the Library System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("  ✗ Invalid choice! Please try again.");
            }
        }
        sc.close();
    }

    // ─── MENU ───────────────────────────────────────────────
    static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║        LIBRARY MANAGEMENT SYSTEM                 ║");
        System.out.println("║    JJ College of Engineering & Technology        ║");
        System.out.println("║                  Trichy                          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    static void printMenu() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                  MAIN MENU                       ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  BOOK MANAGEMENT                                  ║");
        System.out.println("║   1. Add New Book                                 ║");
        System.out.println("║   2. View All Books                               ║");
        System.out.println("║   3. Search Book                                  ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  MEMBER MANAGEMENT                                ║");
        System.out.println("║   4. Add New Member                               ║");
        System.out.println("║   5. View All Members                             ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  ISSUE & RETURN                                   ║");
        System.out.println("║   6. Issue Book to Member                         ║");
        System.out.println("║   7. Return Book from Member                      ║");
        System.out.println("║   8. View All Issue Records                       ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║   9. Library Summary                              ║");
        System.out.println("║  10. Exit                                         ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    // ─── BOOK OPERATIONS ────────────────────────────────────
    static void addBook() {
        System.out.println("\n--- ADD NEW BOOK ---");
        sc.nextLine();
        System.out.print("  Title   : "); String title = sc.nextLine().trim();
        System.out.print("  Author  : "); String author = sc.nextLine().trim();
        System.out.print("  Category: "); String category = sc.nextLine().trim();
        System.out.print("  Copies  : "); int copies = getIntInput();

        books.add(new Book(nextBookId++, title, author, category, copies));
        System.out.println("  ✓ Book added successfully! Book ID: " + (nextBookId - 1));
    }

    static void viewAllBooks() {
        if (books.isEmpty()) { System.out.println("\n  No books found!"); return; }
        System.out.println("\n--- ALL BOOKS ---");
        System.out.printf("  %-5s %-30s %-20s %-15s %s%n",
                "ID", "Title", "Author", "Category", "Status");
        System.out.println("  " + "─".repeat(95));
        for (Book b : books) b.display();
    }

    static void searchBook() {
        System.out.println("\n--- SEARCH BOOK ---");
        System.out.print("  Enter title, author, or ID: ");
        sc.nextLine();
        String query = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(query) ||
                b.getAuthor().toLowerCase().contains(query) ||
                String.valueOf(b.getBookId()).equals(query)) {
                if (!found) {
                    System.out.printf("  %-5s %-30s %-20s %-15s %s%n",
                            "ID", "Title", "Author", "Category", "Status");
                    System.out.println("  " + "─".repeat(95));
                }
                b.display();
                found = true;
            }
        }
        if (!found) System.out.println("  No book found matching: " + query);
    }

    // ─── MEMBER OPERATIONS ──────────────────────────────────
    static void addMember() {
        System.out.println("\n--- ADD NEW MEMBER ---");
        sc.nextLine();
        System.out.print("  Name  : "); String name = sc.nextLine().trim();
        System.out.print("  Email : "); String email = sc.nextLine().trim();
        members.add(new Member(nextMemberId++, name, email));
        System.out.println("  ✓ Member added! Member ID: " + (nextMemberId - 1));
    }

    static void viewAllMembers() {
        if (members.isEmpty()) { System.out.println("\n  No members found!"); return; }
        System.out.println("\n--- ALL MEMBERS ---");
        System.out.printf("  %-6s %-20s %-25s %-10s%n",
                "ID", "Name", "Email", "Books Issued");
        System.out.println("  " + "─".repeat(65));
        for (Member m : members) m.display();
    }

    // ─── ISSUE & RETURN ─────────────────────────────────────
    static void issueBook() {
        System.out.println("\n--- ISSUE BOOK ---");
        System.out.print("  Enter Member ID : "); int mId = getIntInput();
        System.out.print("  Enter Book ID   : "); int bId = getIntInput();

        Member member = findMember(mId);
        Book book = findBook(bId);

        if (member == null) { System.out.println("  ✗ Member not found!"); return; }
        if (book == null)   { System.out.println("  ✗ Book not found!"); return; }
        if (!book.issueBook()) { System.out.println("  ✗ No copies available right now."); return; }
        if (!member.issueBook(bId)) {
            book.returnBook(); // undo
            System.out.println("  ✗ Member already has 3 books. Please return one first.");
            return;
        }

        String date = java.time.LocalDate.now().toString();
        records.add(new IssueRecord(nextRecordId++, mId, member.getName(), bId, book.getTitle(), date));
        System.out.println("  ✓ Book \"" + book.getTitle() + "\" issued to " + member.getName());
        System.out.println("  ✓ Available copies remaining: " + book.getAvailableCopies());
    }

    static void returnBook() {
        System.out.println("\n--- RETURN BOOK ---");
        System.out.print("  Enter Member ID : "); int mId = getIntInput();
        System.out.print("  Enter Book ID   : "); int bId = getIntInput();

        Member member = findMember(mId);
        Book book = findBook(bId);

        if (member == null) { System.out.println("  ✗ Member not found!"); return; }
        if (book == null)   { System.out.println("  ✗ Book not found!"); return; }
        if (!member.returnBook(bId)) { System.out.println("  ✗ This book was not issued to this member!"); return; }

        book.returnBook();

        // Mark latest matching record as returned
        for (int i = records.size() - 1; i >= 0; i--) {
            IssueRecord r = records.get(i);
            if (r.getMemberId() == mId && r.getBookId() == bId && r.getStatus().equals("Issued")) {
                r.markReturned();
                break;
            }
        }
        System.out.println("  ✓ Book \"" + book.getTitle() + "\" returned by " + member.getName());
        System.out.println("  ✓ Available copies now: " + book.getAvailableCopies());
    }

    static void viewIssueRecords() {
        if (records.isEmpty()) { System.out.println("\n  No records found!"); return; }
        System.out.println("\n--- ISSUE RECORDS ---");
        System.out.printf("  %-5s %-20s %-28s %-12s %-10s%n",
                "ID", "Member", "Book", "Issue Date", "Status");
        System.out.println("  " + "─".repeat(80));
        for (IssueRecord r : records) r.display();
    }

    // ─── SUMMARY ────────────────────────────────────────────
    static void viewLibrarySummary() {
        int totalBooks = books.size();
        int totalCopies = books.stream().mapToInt(Book::getTotalCopies).sum();
        int availCopies = books.stream().mapToInt(Book::getAvailableCopies).sum();
        int totalMembers = members.size();
        int totalIssued = (int) records.stream().filter(r -> r.getStatus().equals("Issued")).count();
        int totalReturned = (int) records.stream().filter(r -> r.getStatus().equals("Returned")).count();

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║               LIBRARY SUMMARY                    ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf("║  Total Book Titles    : %-25d║%n", totalBooks);
        System.out.printf("║  Total Copies         : %-25d║%n", totalCopies);
        System.out.printf("║  Available Copies     : %-25d║%n", availCopies);
        System.out.printf("║  Issued Copies        : %-25d║%n", totalCopies - availCopies);
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf("║  Total Members        : %-25d║%n", totalMembers);
        System.out.printf("║  Currently Issued     : %-25d║%n", totalIssued);
        System.out.printf("║  Total Returned       : %-25d║%n", totalReturned);
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    // ─── HELPERS ────────────────────────────────────────────
    static Book findBook(int id) {
        for (Book b : books) if (b.getBookId() == id) return b;
        return null;
    }

    static Member findMember(int id) {
        for (Member m : members) if (m.getMemberId() == id) return m;
        return null;
    }

    static int getIntInput() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("  Enter a valid number: "); }
        }
    }

    // ─── SAMPLE DATA ────────────────────────────────────────
    static void loadSampleData() {
        books.add(new Book(nextBookId++, "Introduction to Java",       "Herbert Schildt",   "Programming", 3));
        books.add(new Book(nextBookId++, "Data Structures in C++",     "Narasimha Karumanchi","DSA",       2));
        books.add(new Book(nextBookId++, "Database Management Systems","Ramakrishnan",       "DBMS",       4));
        books.add(new Book(nextBookId++, "Operating System Concepts",  "Silberschatz",       "OS",         2));
        books.add(new Book(nextBookId++, "Computer Networks",          "Tanenbaum",          "Networks",   3));
        books.add(new Book(nextBookId++, "Python Programming",         "Mark Lutz",          "Programming",2));

        members.add(new Member(nextMemberId++, "Dharshika S",  "dharshika@email.com"));
        members.add(new Member(nextMemberId++, "Priya R",      "priya@email.com"));
        members.add(new Member(nextMemberId++, "Arun K",       "arun@email.com"));
    }
}