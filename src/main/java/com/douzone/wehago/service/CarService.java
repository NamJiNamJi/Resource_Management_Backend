package com.douzone.wehago.service;

import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.Car;
import com.douzone.wehago.dto.car.CarDTO;
import com.douzone.wehago.dto.car.CarPageResponseDTO;
import com.douzone.wehago.dto.car.CarResponseDTO;
import com.douzone.wehago.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public CarResponseDTO saveCar (CarDTO carDTO, MultipartFile image) throws IOException {

        String imageUrl = s3Uploader.upload(image, "car/image");

        Car car = Car.builder()
                .carName(carDTO.getCarName())
                .carNumber(carDTO.getCarNumber())
                .carDistance(carDTO.getCarDistance())
                .carYear(carDTO.getCarYear())
                .carImage(imageUrl)
                .carExplain(carDTO.getCarExplain())
                .copSeq(2)
                .rscSeq(1)
                .build();
        carRepository.save(car);

        return getCarResponseDTO(car);
    }

    @Transactional(readOnly = true)
    public CarPageResponseDTO findAllCar() {

        List<Car> carList = carRepository.findAll();

        List<CarResponseDTO> carResponseDTOList = new ArrayList<>();

        for (Car car : carList) {
            if (car.getCarState() == true) {
                carResponseDTOList.add(getCarResponseDTO(car));
            }
        }

        return CarPageResponseDTO.builder()
                .carList(carResponseDTOList)
                .build();
    }

    @Transactional(readOnly = true)
    public CarPageResponseDTO searchCar (String queryType, String searchString) {
        List<Car> carList = carRepository.searchCar(queryType, searchString);
        System.out.println("service" + queryType + searchString);
        List<CarResponseDTO> carResponseDTOList = new ArrayList<>();

        for (Car car : carList) {
            carResponseDTOList.add(getCarResponseDTO(car));
        }

        return CarPageResponseDTO.builder()
                .carList(carResponseDTOList)
                .build();
    }

    @Transactional(readOnly = true)
    public CarResponseDTO findOneCar (Integer carSeq) {
        Car car = carRepository.findOne(carSeq);

        return getCarResponseDTO(car);
    }

    @Transactional
    public CarResponseDTO updateCar (CarDTO carDTO, MultipartFile image, Integer carSeq) throws IOException {

        String imageUrl = s3Uploader.upload(image, "car/image");

        if (imageUrl != null) {
            s3Uploader.deleteImage(imageUrl, "car/image");
            imageUrl = s3Uploader.upload(image, "car/image");
        }

        Car car = Car.builder()
                .carSeq(carSeq)
                .carName(carDTO.getCarName())
                .carNumber(carDTO.getCarNumber())
                .carDistance(carDTO.getCarDistance())
                .carYear(carDTO.getCarYear())
                .carImage(imageUrl)
                .carExplain(carDTO.getCarExplain())
                .build();

        carRepository.update(car);

        return getCarResponseDTO(car);
    }

    @Transactional
    public void deleteCar (Integer carSeq) {

        Car car = Car.builder()
                .carSeq(carSeq)
                .carState(false)
                .carUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        carRepository.delete(car);
    }

//    public void deleteCar(Integer carSeq) {
//        carRepository.delete(carSeq);
//    }

    private CarResponseDTO getCarResponseDTO (Car car) {
        return CarResponseDTO.builder()
                .carSeq(car.getCarSeq())
                .carName(car.getCarName())
                .carNumber(car.getCarNumber())
                .carDistance(car.getCarDistance())
                .carImage(car.getCarImage())
                .carExplain(car.getCarExplain())
                .carYear(car.getCarYear())
                .carState(car.getCarState())
                .copSeq(car.getCopSeq())
                .rscSeq(car.getRscSeq())
                .build();
    }
}
