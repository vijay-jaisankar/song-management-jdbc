import java.util.Scanner;



public class Demo {
    public static void main(String[] args) {
        Undamped store = new Undamped("root", "Courier1@");
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the store!\n\n\n");
        System.out.println("Press 1 to add a new wallet account, 2 to add a new song, 3 to add a new customer, 4 to show information about a customer, 5 to show information about a song, 6 to delete a song, 7 to delete a customer, 8 to buy a song, 9 to remix the song, and 100 to exit.");

        boolean scanner_closed = false;

        while(sc.hasNextInt()){
            int control = sc.nextInt();
            sc.nextLine();
            String admin_username, admin_password,name,artistUsername,artistPassword;
            int address,id,wallet_address,customer_id,song_id;
            float bal,beats,cost,newBPM;

            switch(control){
                case 1:
                    System.out.println("Please enter the admin username.");
                    admin_username = sc.nextLine();
                    System.out.println("Please enter the admin password.");
                    admin_password = sc.nextLine();
                    System.out.println("Please enter the new wallet address");
                    address = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Please enter the new balance");
                    bal = sc.nextFloat();
                    store.addWalletAccount(admin_username, admin_password, address, bal);
                    break;

                case 2:
                    System.out.println("Please enter the admin username.");
                    admin_username = sc.nextLine();
                    System.out.println("Please enter the admin password.");
                    admin_password = sc.nextLine();
                    System.out.println("Please enter the song id");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Please enter the song name");
                    name = sc.nextLine();
                    System.out.println("Please enter the BPM");
                    beats = sc.nextFloat();
                    System.out.println("Please enter the price");
                    cost = sc.nextFloat();
                    store.addSong(admin_username, admin_password, id, name, beats, cost);
                    break;

                case 3:
                    System.out.println("Please enter the admin username.");
                    admin_username = sc.nextLine();
                    System.out.println("Please enter the admin password.");
                    admin_password = sc.nextLine();
                    System.out.println("Please enter the customer id");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Please enter the customer name");
                    name = sc.nextLine();
                    System.out.println("Please enter the wallet address");
                    wallet_address = sc.nextInt(); 
                    store.addCustomer(admin_username, admin_password, id, name, wallet_address);
                    break;
                
                case 4:
                    System.out.println("Please enter the customer id");
                    id = sc.nextInt();
                    sc.nextLine();
                    store.showInfoAboutCustomer(id);
                    break;
                
                case 5:
                    System.out.println("Please enter the song id");
                    id = sc.nextInt();
                    sc.nextLine();
                    store.showInfoAboutSong(id);
                    break;
                
                case 6:
                    System.out.println("Please enter the admin username.");
                    admin_username = sc.nextLine();
                    System.out.println("Please enter the admin password.");
                    admin_password = sc.nextLine();
                    System.out.println("Please enter the song id");
                    id = sc.nextInt();
                    sc.nextLine();
                    store.deleteSong(admin_username, admin_password, id);
                    break;

                case 7:
                    System.out.println("Please enter the admin username.");
                    admin_username = sc.nextLine();
                    System.out.println("Please enter the admin password.");
                    admin_password = sc.nextLine();
                    System.out.println("Please enter the customer id");
                    id = sc.nextInt();
                    sc.nextLine();
                    store.deleteCustomer(admin_username, admin_password, id);
                    break;
                
                case 8:
                    System.out.println("Please enter the admin username.");
                    admin_username = sc.nextLine();
                    System.out.println("Please enter the admin password.");
                    admin_password = sc.nextLine();
                    System.out.println("Please enter the customer id");
                    customer_id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Please enter the song id");
                    song_id = sc.nextInt();
                    sc.nextLine();
                    store.buySong(admin_username, admin_password, customer_id, song_id);
                    break;

                case 9:
                    System.out.println("Please enter the artist username.");
                    artistUsername = sc.nextLine();
                    System.out.println("Please enter the artist password.");
                    artistPassword = sc.nextLine();
                    System.out.println("Please enter the song ID");
                    song_id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Please enter the new BPM");
                    newBPM = sc.nextFloat();
                    store.remixSong(artistUsername, artistPassword, song_id, newBPM);
                    break;

                case 100:
                    System.out.println("Exiting the system.");
                    sc.close();
                    store.closeAllConnections();
                    scanner_closed = true;
                    return;

                default:
                    System.out.println("Invalid operation.");
                    sc.close();
                    store.closeAllConnections();
                    scanner_closed = true;
                    return;
            }
        }

        if(scanner_closed == false){
            sc.close();
            scanner_closed = true;
        }
    }
}