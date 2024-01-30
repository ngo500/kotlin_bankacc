enum class Account{                                             //types of accounts to pick from
    Debit,
    Credit,
    Checking
}//enum class account

fun main() {
    var accountType = ""                                        //used for the type of bank account
    var userChoice = 0                                          //used for which option user inputs

    println("Welcome to the bank system!")
    println("What type of account would you like to create?")

    for(e in Account.entries){                                  //print options based on enum Account
        println("${e.ordinal+1}. ${e.toString()} account")
    }//for

    while(accountType == ""){
        println("Please enter an option (1, 2, or 3)")          //get input to create account
        //userChoice = readln().toInt()                         //user input
        userChoice = (1..5).random()                            //random input to simulate user option
        println("The selected option is $userChoice!")
        accountType = when(userChoice){
            1 -> {
                Account.Debit.toString()                        //make a debit account
            }//1
            2 -> {
                Account.Credit.toString()                       //make a credit account
            }//2
            3 -> {
                Account.Checking.toString()                     //make a checking account
            }//3
            else -> {
                continue                                        //incorrect input- get new input
            }//else
        }//when
    }//while

    println("You have created a $accountType account!")         //correct input, account created


}//main
