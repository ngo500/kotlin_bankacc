enum class Account{                                             //types of accounts to pick from
    Debit,
    Credit,
    Checking
}//enum class account

enum class Option{                                              //options for doing something with an account
    Withdraw,
    Deposit
}//enum class option

var accountBalance = 0                                          //used as the account balance
var accountType = ""                                            //used for the type of bank account
var money = 0                                                   //used for current deposit/withdraw amount

//deposits dollar amount into balance, and returns dollar amount deposited
fun deposit(amount:Int):Int{
    accountBalance += amount                                    //add amount to total balance
    return amount
}//deposit

fun creditDeposit(amount:Int):Int{
    if(accountBalance == 0){                                    //no balance to pay
        println("Error- Cannot pay balance of $0.00!")
        return accountBalance
    }//if
    else if ((accountBalance+amount) > 0) {                     //balance to pay is less than deposit
        println(
            "Error- cannot deposit $${"%,.2f".format(amount.toDouble())} to account with balance $${
                "%,.2f".format(accountBalance.toDouble())}!")
        return 0
    }//else if                                                  //balance to pay is exact amount of deposit, account paid off
    else if(amount == -accountBalance){
        accountBalance = 0
        println("You have paid off this account.")
        return amount
    }//else if
    else{                                                       //deposit can be made on account balance, call deposit
        return deposit(amount)
    }//else
}//creditDeposit

//takes dollar amount to withdraw from balance, and returns dollar amount withdrawn
fun withdraw(amount:Int):Int{
    accountBalance -= amount                                    //take amount out of total balance
    return amount
}//withdraw

//checks if dollar amount can be withdrawn from account, returns dollar amount withdrawn
fun debitWithdraw(amount:Int):Int{
    if(accountBalance == 0){                                    //no money in account
        println("Error- Cannot withdraw from debit account with balance $0.00!")
        return accountBalance
    }//if
    else if(amount > accountBalance){                           //money in account, but not enough
        println("Error- Cannot withdraw $${"%,.2f".format(amount.toDouble())} from debit account with balance $${"%,.2f".format(accountBalance.toDouble())}!")
        return 0
    }//else if
    else{                                                       //enough money, call withdraw
        return withdraw(amount)
    }//else
}//debitWithdraw

//logic for deciding on whether to deposit/withdraw, given string, calls function, returns nothing
fun transfer(mode:String){
    val transferAmount:Int                                      //used for amount of money transferred
    when(mode){                                                 //check what option is chosen
        Option.Withdraw.toString()-> {                          //withdraw option
            transferAmount = if(accountType == Account.Debit.toString()){
                debitWithdraw(money)                            //withdraw for debit account
            }//if
            else{
                withdraw(money)                                 //withdraw for non-debit account
            }//else
            println("The amount withdrawn is $${"%,.2f".format(transferAmount.toDouble())}!")
        }//withdraw
        Option.Deposit.toString()-> {                           //deposit option
            transferAmount = if(accountType == Account.Credit.toString()){
                creditDeposit(money)                            //deposit for credit account
            }//if
            else{
                deposit(money)                                  //deposit for non-credit account
            }//else
            println("The amount deposited is $${"%,.2f".format(transferAmount.toDouble())}!")
        }//credit
        else->{
            return                                              //improper option given, go back to menu
        }//else
    }//when
}//transfer

fun main() {
    var accountChoice: Int                                      //used for which account option user inputs
    var option: Int                                             //used for which menu option user inputs
    var isSystemOpen = true                                     //used for keeping open/closing app

    println("Welcome to the bank system!")
    println("What type of account would you like to create?")

    for(e in Account.entries){                                  //print options based on enum Account
        println("${e.ordinal+1}. $e account")
    }//for

    while(accountType == ""){
        println("Please enter an option (1, 2, or 3):")         //get input to create account
        //accountChoice = readln().toInt()                      //user input
        accountChoice = (1..5).random()                         //random input to simulate user input
        accountType = when(accountChoice){
            1 -> Account.Debit.toString()                        //make a debit account
            2 -> Account.Credit.toString()                       //make a credit account
            3 -> Account.Checking.toString()                     //make a checking account
            else -> continue                                     //incorrect input, get new input
        }//when
        if(accountType == ""){
            println("ERROR- Invalid option $accountType. Please choose a valid option from the given selection.")
        }//if
        else{
            println("The selected option is $accountChoice!")
        }//else
    }//while

    println("Please make an initial deposit:")                   //initial deposit
    //money = readln().toInt()                                   //user input
    money = (1..1000).random()                                   //random input to simulate user input
    deposit(money)
    println("The initial balance is $${"%,.2f".format(accountBalance.toDouble())}!")

    println("You have created a $accountType account!")         //correct input, account created

    while(isSystemOpen){                                        //while user doesn't request to exit menu
        println("What would you like to do?")                   //print menu guide
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit Money")
        println("4. Close the app")
        println("Please choose an option: (1, 2, 3, or 4)")

        //option = readln().toInt()                              //user input for menu option
        option = (1..5).random()                                 //random input to simulate user input
        println("You entered $option!")

        when(option){                                           //handle option input
            1 -> {                                              //print balance
                println("The balance of the account is $${"%,.2f".format(accountBalance.toDouble())}")
            }//1
            2 -> {                                              //withdraw from account
                println("Please enter amount to withdraw: ")
                //money = readln().toInt()                      //user input
                money = (1..1000).random()                      //random input to simulate user input
                transfer(Option.Withdraw.toString())
            }//2
            3 -> {                                              //deposit to account
                println("Please enter amount to deposit: ")
                //money = readln().toInt()                      //user input
                money = (1..1000).random()                      //random input to simulate user input
                transfer(Option.Deposit.toString())
            }//3
            4 -> {                                              //exit menu
                isSystemOpen = false
                println("The system is closed now.")
            }//4
            else -> {                                           //invalid option, print menu again and input again
                println("ERROR- Invalid option $option. Please choose a valid option from the given selection.")
                continue
            }//else
        }//when
    }//while
}//main
