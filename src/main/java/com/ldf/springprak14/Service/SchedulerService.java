package com.ldf.springprak14.Service;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
@Slf4j
public class SchedulerService {

    @Value("${export.directory}")
    private String exportDirectory;

    private final MarketService marketService;
    private final ProductService productService;

    public SchedulerService(MarketService marketService, ProductService productService) {
        this.marketService = marketService;
        this.productService = productService;
    }

    public void clearDirectory() throws IOException {
        Files.walk(Paths.get(exportDirectory))
                .filter(Files::isRegularFile)
                .map(java.nio.file.Path::toFile)
                .forEach(File::delete);
    }

    // Метод для экспорта данных из базы данных в файлы
    public void exportData() throws IOException {
        // получение данных из базы данных
        List<Market> markets = marketService.findAll();
        List<Product> products = productService.getAllProducts();

        // сохранение данных в файлы
        for (Market market : markets) {
            String fileName = exportDirectory + File.separator + market.getId() + ".txt";
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(market.toString());
            }
        }

        for (Product product : products) {
            String fileName = exportDirectory + File.separator + product.getId() + ".txt";
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(product.toString());
            }
        }
    }

    @Scheduled(fixedRate = 60 * 1000) // каждые 30 минут
    public void scheduledExport() throws IOException {
        clearDirectory();
        exportData();
        log.info("Data was exported");
    }

    // Метод для вызова через JMX
    public void exportDataViaJmx() throws IOException {
        clearDirectory();
        exportData();
    }
}
