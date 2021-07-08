package by.epam.shapes.reader;

import by.epam.shapes.exception.ReaderException;

public interface Reader {
    String readPoints() throws ReaderException;
}
