package Control;

import View.Dialog;

public class SwapCommand implements Command {
    private final Dialog dialog;

    public SwapCommand(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void execute() {
        dialog.swapCurrencies();
    }
}
