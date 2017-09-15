package Logic;

public class Validity {
	
	public static boolean checkCost (Player player, int cardNumber) {
		boolean valid = true;
		if(player.getMoney() < player.getHand().getCard(cardNumber).getCost()) {
			System.out.println("You do not have enough money to lay this card");
			System.out.println("Please input new card number (input -1 to cancel): ");
			valid = false;
			//cardNumber = Instruction.selectCard();
		}
		return valid;
	}
}
