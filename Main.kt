enum class Account{                                             //types of accounts to pick from
    Debit,
    Credit,
    Checking
}//enum class account

var accountBalance = 0                                          //used as the account balance

//takes dollar amount to withdraw from balance, and returns dollar amount withdrawn
fun withdraw(amount:Int):Int{
    accountBalance -= amount                                    //take amount out of total balance
    println("You withdrew $${"%,.2f".format(amount.toDouble())} from the account!")
    println("The remaining account balance is $${"%,.2f".format(accountBalance.toDouble())}!")
    return amount
}//withdraw

fun main() {
    var accountType = ""                                        //used for the type of bank account
    var userChoice = 0                                          //used for which option user inputs
    //var accountBalance = 0                                    //used as the account balance
    var output = 0                                              //output for deposit/withdraw functions
    var money = 0

    println("Welcome to the bank system!")
    println("What type of account would you like to create?")

    for(e in Account.entries){                                  //print options based on enum Account
        println("${e.ordinal+1}. ${e.toString()} account")
    }//for

    while(accountType == ""){
        println("Please enter an option (1, 2, or 3):")         //get input to create account
        //userChoice = readln().toInt()                         //user input
        userChoice = (1..5).random()                            //random input to simulate user input
        println("The selected option is $userChoice!")
        accountType = when(userChoice){
            1 -> Account.Debit.toString()                        //make a debit account
            2 -> Account.Credit.toString()                       //make a credit account
            3 -> Account.Checking.toString()                     //make a checking account
            else -> continue                                     //incorrect input- get new input
        }//when
    }//while

    println("You have created a $accountType account!")           //correct input, account created
    println("Please make an initial deposit:")
    //accountBalance = readln().toDouble()                        //user input
    accountBalance = (1..1000).random()                           //get initial balance
    println("The initial balance is $${"%,.2f".format(accountBalance.toDouble())}!")

    money = (1..accountBalance).random()
    output = withdraw(money)
    println("You have successfully withdrawn $${"%,.2f".format(output.toDouble())}!")
}//main
