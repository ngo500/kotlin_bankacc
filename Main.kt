enum class Account{                                             //types of accounts to pick from
    Debit,
    Credit,
    Checking
}//enum class account

var accountBalance = 0                                          //used as the account balance
var accountType = ""                                            //used for the type of bank account
var money = 0                                                   //used for current deposit/withdraw amount
var isSystemOpen = true                                         //used for keeping open/closing app
var option = 0

//deposits dollar amount into balance, and returns dollar amount deposited
fun deposit(amount:Int):Int{
    accountBalance += amount                                    //add amount to total balance
    println("You deposited $${"%,.2f".format(amount.toDouble())} to the account!")
    println("The current account balance is $${"%,.2f".format(accountBalance.toDouble())}!")
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
    println("You withdrew $${"%,.2f".format(amount.toDouble())} from the account!")
    println("The remaining account balance is $${"%,.2f".format(accountBalance.toDouble())}!")
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
    val transferAmount:Int
    when(mode){
        Account.Debit.toString()-> {
            transferAmount = if(accountType == Account.Debit.toString()){
                debitWithdraw(money)
            }//if
            else{
                withdraw(money)
            }//else
            println("The amount withdrawn is $${"%,.2f".format(transferAmount.toDouble())}!")
        }//withdraw
        Account.Credit.toString()-> {
            transferAmount = if(accountType == Account.Credit.toString()){
                creditDeposit(money)
            }//if
            else{
                deposit(money)
            }//else
            println("The amount deposited is $${"%,.2f".format(transferAmount.toDouble())}!")
        }//credit
        else->{
            return
        }//else
    }//when

}//transfer

fun main() {
    //var accountType = ""                                      //used for the type of bank account
    var userChoice = 0                                          //used for which option user inputs
    //var accountBalance = 0                                    //used as the account balance
    var output = 0                                              //output for deposit/withdraw functions
    //var money = 0

    println("Welcome to the bank system!")
    println("What type of account would you like to create?")

    for(e in Account.entries){                                  //print options based on enum Account
        println("${e.ordinal+1}. ${e.toString()} account")
    }//for

    while(accountType == ""){
        println("Please enter an option (1, 2, or 3):")         //get input to create account
        //userChoice = readln().toInt()                         //user input
        userChoice = (1..5).random()                      //random input to simulate user input
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
    money = (1..1000).random()                              //get initial balance
    deposit(money)
    println("The initial balance is $${"%,.2f".format(accountBalance.toDouble())}!")
    accountBalance = -money
    creditDeposit(money)
    accountBalance = 1000

    money = (1..accountBalance).random()
    output = debitWithdraw(money)
    println("You have successfully withdrawn $${"%,.2f".format(output.toDouble())}!")
}//main
