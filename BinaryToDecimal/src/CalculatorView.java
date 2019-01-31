import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {

    private JLabel output;
    private JLabel altOutput;
    private JButton zeroButton;
    private JButton oneButton;
    private JButton clearButton;
    private JButton plusButton;
    private JButton minusButton;
    private JButton multButton;
    private JButton equalsButton;
    private JButton divideButton;
    private JButton negButton;
    private int evaluationTotal=0;
    private int temp=0;
    
    
    // 'q'== else or default
    private char lastOperatorPressed='q';

    public CalculatorView(){
        createView();
        this.setTitle("Binary Calculator");
        setLayout(new GridLayout(1,5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(370,400));
       
        setResizable(false);
        setVisible(true);
        pack();
    }

    private void createView(){
        JPanel panel= new JPanel();
        JPanel outputPanel=  new JPanel();
        outputPanel.setPreferredSize(new Dimension(312,50));

        outputPanel.setBackground(Color.white);

        output= new JLabel("");
        altOutput= new JLabel("");
        altOutput.setPreferredSize(new Dimension(180,15));
        output.setFont(new Font("Serif", Font.PLAIN, 20));
        

        zeroButton= new JButton("0");
        plusButton= new JButton("+");
        oneButton= new JButton("1");
        minusButton= new JButton("-");
        equalsButton= new JButton("=");
        multButton= new JButton("X");
        clearButton= new JButton("CE");
        divideButton= new JButton("/");
        negButton= new JButton("+/-");
        
        zeroButton.setPreferredSize(new Dimension(100,60));
        plusButton.setPreferredSize(new Dimension(100,60));
        oneButton.setPreferredSize(new Dimension(100,60));
        minusButton.setPreferredSize(new Dimension(100,60));
        equalsButton.setPreferredSize(new Dimension(100,60));
        multButton.setPreferredSize(new Dimension(100,60));
        clearButton.setPreferredSize(new Dimension(100,60));
        divideButton.setPreferredSize(new Dimension(100,60));
        negButton.setPreferredSize(new Dimension(100,60));
        
        allClickEvents();
        
       
        outputPanel.add(output);
        panel.add(outputPanel);
        panel.add(zeroButton);
        panel.add(plusButton);
        panel.add(minusButton);
        panel.add(oneButton);
        panel.add(divideButton);
        panel.add(multButton);
        panel.add(clearButton);
        panel.add(equalsButton);
        panel.add(altOutput);
        panel.add(negButton); 
        panel.add(altOutput);

        getContentPane().add(panel);
    }
    
    private void allClickEvents() {
    	zeroButton.addActionListener(new ZeroButtonClick());
    	oneButton.addActionListener(new OneButtonClick());
    	clearButton.addActionListener(new ClearButtonClick());
    	equalsButton.addActionListener(new EqualsButtonClick());
    	plusButton.addActionListener(new PlusButtonClick());
    	minusButton.addActionListener(new MinusButtonClick());
    	multButton.addActionListener(new MultButtonClick());
    	divideButton.addActionListener(new DivideButtonClick());
    	negButton.addActionListener(new NegButtonClick());
    }
    
    private class ZeroButtonClick implements ActionListener{
    	
    	public void actionPerformed(ActionEvent event) {
    		String text= output.getText();
    		text+="0";
    		output.setText(text);
    		
    		String altText= altOutput.getText();
    		altOutput.setText(altText+"0");
    		
    	}
    }
    
    private class OneButtonClick implements ActionListener{
    	
    	public void actionPerformed(ActionEvent event) {
    		String text= output.getText();
    		text+="1";
    		output.setText(text);
    		
    		String altText= altOutput.getText();
    		altOutput.setText(altText+"1");
    	}
    }
    
    
    private class ClearButtonClick implements ActionListener{
    	
    	public void actionPerformed(ActionEvent event) {
    		enableButtons();
    		output.setText("");
    		evaluationTotal=0;
    		temp=0;
    		
    		altOutput.setText("");
    	}
    }
    
    private class PlusButtonClick implements ActionListener{
    	
    	
    	public void actionPerformed(ActionEvent event) {
    		
    		//Catches exceptions for faulty operator
    		
    		operatorFunctions();
    		
    		lastOperatorPressed='+';
    		
    		if(isCorrectAltOutput()) {
    		String altText= altOutput.getText();
    		altOutput.setText(altText+"+");
    		}
    		
    	}
    }
    
    private class MinusButtonClick implements ActionListener{
    	
    	public void actionPerformed(ActionEvent event) {
    		
        		operatorFunctions();
        		
    		lastOperatorPressed='-';
    		
    		if(isCorrectAltOutput()) {
        		String altText= altOutput.getText();
        		altOutput.setText(altText+"-");
        		}
    	}
    }
    
	 private class MultButtonClick implements ActionListener{
	    	
	    	public void actionPerformed(ActionEvent event) {
	    		
	        		operatorFunctions();
	        		
	    		lastOperatorPressed='x';
	    		
	    		if(isCorrectAltOutput()) {
	        		String altText= altOutput.getText();
	        		altOutput.setText(altText+"x");
	        		}
	    	}
	 }
	
	private class DivideButtonClick implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
	    		operatorFunctions();
	    		
    		lastOperatorPressed='÷';
    		
    		if(isCorrectAltOutput()) {
        		String altText= altOutput.getText();
        		altOutput.setText(altText+"÷");
    		}
		}
	}
	
	private class NegButtonClick implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			String num= output.getText();
			num+="-";
			output.setText(num);
			
			
		}
	}

	
	private class EqualsButtonClick implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			int BinaryNum;
			String num;
			
			
			if(output.getText().equals("")) {
				altOutput.setText("No Input".toUpperCase());
				output.setText("No Input".toUpperCase());
				disableButtons();
				return;
			}
			else if(divisionByZeroError()){
				altOutput.setText("Division by Zero Error".toUpperCase());
				output.setText("Division by Zero Error".toUpperCase());
				disableButtons();
				return;
			}
			
			if(isCorrectAltOutput()) {
	    		String altText= altOutput.getText();
	    		altOutput.setText(altText+"=");
	    		}
			
			switch(lastOperatorPressed) {
				case '+': 
					num= output.getText();
					BinaryNum=BinaryDecimal.BinaryToDecimal(num);
					evaluationTotal=temp+BinaryNum;
					output.setText(BinaryDecimal.DecimalToBinary((evaluationTotal)));
					break;
				case '-':
					num= output.getText();
					BinaryNum=BinaryDecimal.BinaryToDecimal(num);
					evaluationTotal=temp-BinaryNum;
					output.setText(BinaryDecimal.DecimalToBinary((evaluationTotal)));
					break;
				case 'x':
					num= output.getText();
					BinaryNum=BinaryDecimal.BinaryToDecimal(num);
					evaluationTotal=temp*BinaryNum;
					output.setText(BinaryDecimal.DecimalToBinary((evaluationTotal)));
					break;
				case '÷':
					num= output.getText();
					BinaryNum=BinaryDecimal.BinaryToDecimal(num);
					evaluationTotal=temp/BinaryNum;
					output.setText(BinaryDecimal.DecimalToBinary((evaluationTotal)));
					break;
				case'q':
					num= output.getText();
					BinaryNum=BinaryDecimal.BinaryToDecimal(num);
					evaluationTotal=BinaryNum;
					output.setText(BinaryDecimal.DecimalToBinary((evaluationTotal)));
					break;
					
			
			}
			//WTFFFFF
			lastOperatorPressed='q';

			
			
		}
	}
		
		// Checks for correct function of calculator
		private void operatorFunctions(){
			int BinaryNum;
			String num;
			try {
			
			if(evaluationTotal==0||lastOperatorPressed=='q'||temp==0) {
	    		num= output.getText();
	    		BinaryNum= BinaryDecimal.BinaryToDecimal(num);
	    		temp=BinaryNum;
	    		output.setText("");
	    		return;
	    	}
			if(lastOperatorPressed=='x') {
    			 num= output.getText();
        		 BinaryNum= BinaryDecimal.BinaryToDecimal(num);
        		evaluationTotal= temp*BinaryNum;
        		output.setText("");
        		temp=evaluationTotal;
    		}
    		
    		if(lastOperatorPressed=='-') {
    			num= output.getText();
        		BinaryNum= BinaryDecimal.BinaryToDecimal(num);
        		evaluationTotal= temp-BinaryNum;
        		output.setText("");
        		temp=evaluationTotal;

    		}
    		
    		if(lastOperatorPressed=='÷') {
    			num= output.getText();
        		BinaryNum= BinaryDecimal.BinaryToDecimal(num);
        		evaluationTotal= temp/BinaryNum;
        		output.setText("");
        		temp=evaluationTotal;

    		}
    		
    		if(lastOperatorPressed=='+') {
    			num= output.getText();
        		BinaryNum= BinaryDecimal.BinaryToDecimal(num);
        		evaluationTotal= temp+BinaryNum;
        		output.setText("");
        		temp=evaluationTotal;
    		}
			}catch(Exception e) {
				output.setFont(new Font("Serif", Font.PLAIN, 18));
    			output.setText("Error. Please Clear".toUpperCase());
    			disableButtons();
    			
			}
    		
	}
		
		
		// Checks if number are inserted first before an operator
		private boolean isCorrectAltOutput() {
			if(!altOutput.getText().equals(""))
				return true;
			else return false;
			
		}
		
		
		
		private boolean divisionByZeroError(){
			if(altOutput.getText().contains("0÷0") || altOutput.getText().contains("1÷0")||altOutput.getText().contains("=÷0")){
				return true;
			}
			return false;
		}
		
		
		private void disableButtons() {
			oneButton.setEnabled(false);
			zeroButton.setEnabled(false);
			plusButton.setEnabled(false);
			minusButton.setEnabled(false);
			divideButton.setEnabled(false);
			multButton.setEnabled(false);
			negButton.setEnabled(false);
			equalsButton.setEnabled(false);
			
			
		}
		
		private void enableButtons() {
			oneButton.setEnabled(true);
			zeroButton.setEnabled(true);
			plusButton.setEnabled(true);
			minusButton.setEnabled(true);
			divideButton.setEnabled(true);
			multButton.setEnabled(true);
			negButton.setEnabled(true);
			equalsButton.setEnabled(true);
		}
			
}


