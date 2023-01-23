package com.example.ru_kam_third_courcework.service;

import com.example.ru_kam_third_courcework.dto.SocksRequest;
import com.example.ru_kam_third_courcework.exception.DamageSocksRequestException;
import com.example.ru_kam_third_courcework.exception.LowSocksQuantityException;
import com.example.ru_kam_third_courcework.model.Color;
import com.example.ru_kam_third_courcework.model.Size;
import com.example.ru_kam_third_courcework.model.Socks;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocksService {

    private final Map<Socks, Integer> socksPair = new HashMap<>();


    //Метод прихода товара(носков)
    public void addSocks(SocksRequest socksRequest) {
        validateRequest(socksRequest);
        Socks socks = mapToSocks(socksRequest);
        if (socksPair.containsKey(socks)) {
            socksPair.put(socks, socksPair.get(socks) + socksRequest.getQuantity());
        } else {
            socksPair.put(socks, socksRequest.getQuantity());
        }
    }


    //Метод отгрузки товара(носков)
    public void deliverySocks(SocksRequest socksRequest) {
        decreaseSocksQuantity(socksRequest);
    }

    //Метод списания товара(носков) с браком
    public void removeDefectSocks(SocksRequest socksRequest) {
      decreaseSocksQuantity(socksRequest);

    }


    //Ощий метод удаления товара(носков) - одна имплементация в приватном методе
    private void decreaseSocksQuantity(SocksRequest socksRequest) {
        validateRequest(socksRequest);
        Socks socks = mapToSocks(socksRequest);
        int socksQuantity = socksPair.getOrDefault(socks, 0);
        if (socksQuantity >= socksRequest.getQuantity()) {
            socksPair.put(socks, socksQuantity - socksRequest.getQuantity());
        } else {
            throw new LowSocksQuantityException("Недостаточное количество товара на складе.");
        }
    }

    //Метод вывода общего количества товара(носков) подходящих по параметрам
    public int getSocksQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for (Map.Entry<Socks, Integer>entry:socksPair.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonRatio() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonRatio() > cottonMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    //Метод проверки правильности заполнения полей
    private void validateRequest(SocksRequest socksRequest) {
        if (socksRequest.getColor() == null || socksRequest.getSize() == null){
            throw new DamageSocksRequestException("Все поля должны быть заполнены.");
        }
        if (socksRequest.getCottonRatio() < 0 || socksRequest.getCottonRatio() > 100) {
            throw new DamageSocksRequestException("Содержание хлопка должно составлять от 0 до 100%.");
        }
        if (socksRequest.getQuantity() <= 0) {
            throw new DamageSocksRequestException("Количество пар носков должно быть больше 0.");
        }
    }

    private Socks mapToSocks(SocksRequest socksRequest) {
        return new Socks(socksRequest.getColor(),
                socksRequest.getSize(), socksRequest.getCottonRatio());
    }

}
