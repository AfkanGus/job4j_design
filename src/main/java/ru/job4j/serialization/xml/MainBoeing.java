package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 4. JAXB. Преобразование XML в POJO. [#315063 # [#315063 #435145].
 * Демонстрация примера как сериализовать/десериализовать объекты в/c XML.
 * 1. Создаем объект и передаем аргументы в его коструктор.
 * 2. Создаем JAXBContext - для аннатированных классов - это фабрика для создания объктов
 * серилизации и десирилизации.
 * 3. Создаем Marshaller(серилизатор) для объекта Boeing, с установкой свойства быть отфарматированным,
 * что бы были отступы и новые строки для создания структуры и читабельности.
 * 4. Создаем пустую строку для сохранения XML формата объекта Boeing.
 * 5. Записываем серилизованный объект в XML.
 * 6. Создаем десериализатор и читаем объект c XML.
 */
public class MainBoeing {
    public static void main(String[] args) throws JAXBException, IOException {
        Boeing boeing = new Boeing(true, 900, "Boeing 737",
                new Boeing.Engine("Turbofan", 28000),
                new String[]{"First Class", "Business Class", "Economy Class"}
        );
        JAXBContext context = JAXBContext.newInstance(Boeing.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(boeing, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Boeing rsl = (Boeing) unmarshaller.unmarshal(reader);
            System.out.println(rsl);
        }

    }
}
