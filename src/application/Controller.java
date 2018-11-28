package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

	@FXML
	private TextField txtCity;
	@FXML
	private Button btnSearch;
	@FXML
	private Label lblTemp;
	@FXML
	private Label lblRain;
	@FXML
	private Label lblWeather;
	@FXML
	private Label lblCity;

	ParserDOM parser = new ParserDOM();

	@FXML
	public void initialize() {
		doTheThing();
	}

	@FXML
	public void btnSeach_Click(ActionEvent event) {
		doTheThing();
	}

	@FXML
	public void txtCity_Enter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			doTheThing();
		}
	}

	public void doTheThing() {
		String cityName = txtCity.getText();
		String[] array = new String[4];

		array = parser.parser(cityName);

		lblCity.setText(array[0]);
		lblTemp.setText(array[1]);
		lblRain.setText(array[2]);
		lblWeather.setText(array[3]);
	}
}
