package by.epam.shapes.dao.reader;

import by.epam.shapes.helper.exception.ReaderException;

import java.util.List;

public interface Reader {
    List<String> readPoints() throws ReaderException;
}
