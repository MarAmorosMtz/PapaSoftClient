package com.example.papasoftclient.controllers.main;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AcercaDeController {
    public void handleEmail1() {
        openEmail("l21020339@veracruz.tecnm.mx");
    }

    public void handleEmail2() {
        openEmail("l21020397@veracruz.tecnm.mx");
    }

    public void handleEmail3() {
        openEmail("l21020366@veracruz.tecnm.mx");
    }

    public void handleEmail4() {
        openEmail("l21020418@veracruz.tecnm.mx");
    }

    public void handleEmail5() {
        openEmail("l21020459@veracruz.tecnm.mx");
    }

    public void handleEmail6() {
        openEmail("l21020400@veracruz.tecnm.mx");
    }

    private void openEmail(String email) {
        try {
            URI uri = new URI("mailto:" + email + "?subject=Solicitud%20de%20Ayuda/Mejora%20PAPASOFT");
            Desktop.getDesktop().mail(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
