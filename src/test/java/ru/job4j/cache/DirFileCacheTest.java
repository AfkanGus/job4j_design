package ru.job4j.cache;

import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 1. Реализация кеша на SoftReference [#1592]
 */
class DirFileCacheTest {
    private static final String TEMP_DIR = "./test_cache";
    private static final String TEST_FILE = "test.txt";
    private static final String FILE_CONTENT = "Hello, Cache!";
    private DirFileCache cache;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Path.of(TEMP_DIR));
        Files.writeString(Path.of(TEMP_DIR, TEST_FILE), FILE_CONTENT, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        cache = new DirFileCache(TEMP_DIR);
    }

    @Test
    void whenLoadFileThenCacheReturnsContent() {
        String expectedContent = FILE_CONTENT;
        String actualContent = cache.get(TEST_FILE);
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void whenFileIsNotFoundThenCacheReturnsNull() {
        String nonExistentFile = "non_existing.txt";
        String result = cache.get(nonExistentFile);
        assertNull(result);
    }

    @Test
    void whenPutFileThenCacheStoresIt() {
        String newFile = "new_file.txt";
        String newContent = "Cached content";
        cache.put(newFile, newContent);
        String cachedContent = cache.get(newFile);
        assertEquals(newContent, cachedContent);
    }
}