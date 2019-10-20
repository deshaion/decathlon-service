package com.test.writer;

import com.test.error.AppException;
import com.test.participant.EventPerformance;
import com.test.participant.RankedParticipant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DecathlonResultWriter implements Writer {
    private String xmlFilePath;
    private Document document;

    public DecathlonResultWriter(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public void write(List<RankedParticipant> participants) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();

            writeElements(participants);

            // create the xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new AppException("Exception while write to XML file", e);
        }
    }

    private void writeElements(List<RankedParticipant> participants) {
        Element root = document.createElement("Participants");
        document.appendChild(root);

        for (RankedParticipant participant : participants) {
            root.appendChild(getParticipantElement(participant));
        }
    }

    private Element getParticipantElement(RankedParticipant participant) {
        Element participantElement = document.createElement("Participant");

        participantElement.appendChild(element("Rank", participant.getRank()));
        participantElement.appendChild(element("TotalScore", String.valueOf(participant.getScoredParticipant().getTotalScore())));
        participantElement.appendChild(element("Name", participant.getScoredParticipant().getParticipant().getName()));

        for (EventPerformance eventPerformance : participant.getScoredParticipant().getParticipant().getEventPerformanceList()) {
            participantElement.appendChild(element(String.valueOf(eventPerformance.getEvent()), eventPerformance.getOriginalPerformance()));
        }

        return participantElement;
    }

    private Element element(String tagName, String text) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(text));
        return element;
    }
}
