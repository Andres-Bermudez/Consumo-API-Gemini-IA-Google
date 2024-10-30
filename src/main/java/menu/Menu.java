package menu;

import gemini.SolicitudHttpAPI;
import modelos.ResponseData;
import java.util.Scanner;

public class Menu {

    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        String menu = """
                      *************************************************************
                                    CONSULTORIA SOBRE EMPRENDIMIENTO
                      Esta es una conversación con una Inteligencia Artificial
                      
                      Como consultor sobre emprendimiento, es fundamental entender
                      la situación, objetivos y desafíos específicos del cliente
                      para ofrecer una asesoría personalizada y efectiva.
                      
                      Esta es una lista de preguntas clave para brindarte mi ayuda:
                      """;

        System.out.println(menu);
        System.out.println("PRESIONA ENTER PARA CONTINUAR...");
        String vacio = sc.nextLine();

        System.out.println("\nSOBRE LA IDEA DE NEGOCIO" + "\n--------------------------------------------------------");

        System.out.print("\n¿Cuál es tu idea de negocio?\n" +
                "Describe tu producto o servicio en detalle: ");
        String ideaNegocio = sc.nextLine();

        System.out.print("\n¿Qué problema estás resolviendo con tu negocio?: ");
        String problemaQueResuelvo = sc.nextLine();

        System.out.print("\n¿Por qué es importante este problema y para quién?: ");
        String importanciaProblema = sc.nextLine();

        System.out.print("\n¿Qué te motivó a emprender este negocio?: ");
        String motivacionEmprendimiento = sc.nextLine();

        System.out.print("\nSOBRE EL MERCADO Y COMPETENCIA" + "\n----------------------------------------------------");

        System.out.print("\n¿Quién es tu cliente ideal?: ");
        String clienteIdeal = sc.nextLine();

        System.out.print("\n¿Has realizado algún estudio de mercado? Si o No: ");
        String estudioMercado = sc.nextLine();

        System.out.print("\n¿Qué descubriste sobre la demanda de tu producto o servicio?: ");
        String descubrimientoDemandaProducto = sc.nextLine();

        System.out.print("\n¿Quiénes son tus principales competidores?: ");
        String principalesCompetidores = sc.nextLine();

        System.out.print("\n¿Qué están haciendo bien y en qué áreas crees que puedes superarlos?: ");
        String areasSuperarCompetidores = sc.nextLine();

        System.out.print("\nSOBRE EL MODELO DE NEGOCIO" + "\n----------------------------------------------------");

        System.out.print("\n¿Cuál es tu propuesta de valor única?: ");
        String propuestaValorUnica = sc.nextLine();

        System.out.print("\n¿Qué te diferencia de la competencia?: ");
        String diferenciaCompetencia = sc.nextLine();

        enviarFormulario(ideaNegocio, problemaQueResuelvo, importanciaProblema, motivacionEmprendimiento,
                clienteIdeal,estudioMercado, descubrimientoDemandaProducto, principalesCompetidores,
                areasSuperarCompetidores, propuestaValorUnica, diferenciaCompetencia);
    }

    private void enviarFormulario(String ideaNegocio,
                                  String problemaQueResuelvo,
                                  String importanciaProblema,
                                  String motivacionEmprendimiento,
                                  String clienteIdeal,
                                  String estudioMercado,
                                  String descubrimientoDemandaProducto,
                                  String principalesCompetidores,
                                  String areasSuperarCompetidores,
                                  String propuestaValorUnica,
                                  String diferenciaCompetencia) {

        String prompt = String.format("Imagina que eres un consultor especializado en emprendimiento. " +
                "A continuación, te proporcionaré algunos datos sobre mi futura empresa y quiero " +
                "que elabores un informe detallado sobre la viabilidad de mi negocio y otros aspectos: " +
                "Mi idea de negocio es %s, " + "me encargaria de resolver %s" + " , este problema es " +
                "importante porque %s , lo que me motivo a crear este emprendimiento es %s y mi " +
                "cliente ideal seria %s, %s he realizado un estudio de mercado." + "Descubri que %s y que " +
                "mis principales competidores son %s y ellos son buenos en %s y mi propuesta de" +
                " valor unica es %s y me diferencio porque %s. ", ideaNegocio, problemaQueResuelvo, importanciaProblema,
                motivacionEmprendimiento, clienteIdeal, estudioMercado, descubrimientoDemandaProducto,
                principalesCompetidores, areasSuperarCompetidores, propuestaValorUnica, diferenciaCompetencia);

        System.out.println("\n---------------------------------------------------------------------------"
                + "\nConsultando a Gemini.....");
        imprimirRespuesta(prompt);
    }

    private void imprimirRespuesta(String prompt) {
        SolicitudHttpAPI solicitud = new SolicitudHttpAPI();
        ResponseData respuestaGemini = solicitud.solicitudGeminiAPI(prompt);

        if (respuestaGemini != null) {
            System.out.println("\n********************************************************************************************************");
            System.out.println("RESPUESTA DE GEMINI:\n");
            System.out.println(respuestaGemini.getCandidates().get(0).getContent().getParts().get(0).getText());
            System.out.println("**********************************************************************************************************");
            System.out.println("\nRazón de finalización: " + respuestaGemini.getCandidates().get(0).getFinishReason());
            System.out.println("Número total de tokens: " + respuestaGemini.getUsageMetadata().getTotalTokenCount());

        } else {
            System.out.println("\nLa respuesta esta vacia. NULL");
        }
    }

    public void menuPrueba() {
        Scanner sc = new Scanner(System.in);

        System.out.print("::::::::::::::::::::" + "\nEscribe el prompt: ");
        String prompt = sc.nextLine();

        System.out.println("\nConsultando a Gemini...");

        imprimirRespuesta(prompt);
    }
}
