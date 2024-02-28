package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Repository.AbstractRepository;
import com.example.PlaceAdminister.Repository.TableRepository;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ReservationService {

    @Autowired
    private AbstractRepository abstractRepository;

    @Autowired
    private TableRepository tableRepository;

    private String reservationFilePath = "src/main/resources/Reservations.json";
    private String TableFilePath = "src/main/resources/Tables.json";

    private final ResourceLoader resourceLoader;

    public ReservationService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public List<ReservationDTO>  getAllReservations(){
        Resource resource = resourceLoader.getResource("classpath:Reservations.json");
        return abstractRepository.readFromJsonReservation(resource);
    }

    public ReservationDTO reserve(ReservationDTO reservationDTO){
        Resource resource = resourceLoader.getResource("classpath:Reservations.json");

        String filePath = "src/main/resources/Reservations.json";
//        System.out.println(reservationDTO.getNum_of_seats());
       List<ReservationDTO> reservationDTOList =  abstractRepository.readFromJsonReservation(resource);

       if(reservationDTO.getType() ==1){
            ReservationDTO existedReservation=  reservationDTOList.stream()
                   .filter(i ->i.getRoom_id().equals(reservationDTO.getRoom_id()))
                    .filter(j -> (j.getTime().getHours() == reservationDTO.getTime().getHours()) || (j.getTime().getHours() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHours()))
                   .findFirst().orElse(null) ;
            if(existedReservation != null){
                return new ReservationDTO("Sorry, This room in this time is already reserved");
            }

       }
       else if (reservationDTO.getType() == 2){
           ReservationDTO existedReservation = reservationDTOList.stream()
                   .filter(i->i.getTable_id().equals(reservationDTO.getTable_id()))
                   .filter(j -> (j.getTime().getHours() == reservationDTO.getTime().getHours())
                           || (j.getTime().getHours() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHours()))
                   .findFirst().orElse(null);

           Resource tableResource = resourceLoader.getResource("classpath:Tables.json");
           TableEntity tableDTO = tableRepository.getById(reservationDTO.getTable_id());

           if(tableDTO == null){
               return new ReservationDTO("Sorry, we can't find this table in our place");
           }


           ReservationDTO existedRoomReservation = reservationDTOList.stream()
                   .filter(i->i.getRoom_id().equals(tableDTO.getRoom()))
                   .filter(j -> (j.getTime().getHours() == reservationDTO.getTime().getHours())
                           || (j.getTime().getHours() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHours()))
                   .findFirst().orElse(null);

           if(existedReservation != null){
               return new ReservationDTO("Sorry, this table is already reserved in this time");
           }
           if (existedRoomReservation != null){
               return new ReservationDTO("Sorry, room that you want to reserve in is already reserved in this time");
           }
       }


       // this case 3 did not hand perfectly
//       if(reservationDTO.getType() == 3){
//           // if table is reserved..
//           ReservationDTO existedReservation = reservationDTOList.stream()
//                   .filter(i->i.getTable_id().equals(reservationDTO.getTable_id()))
//                   .filter(j -> j.getTime().getHours() == reservationDTO.getTime().getHours())
//                   .findFirst().orElse(null);
//
//           // if number of available seats is enough
//           TableEntity tableDTO = tableRepository.getById(reservationDTO.getTable_id() );
////           TableDTO tableDTO = tableDTOList.stream()
////                   .filter(i->i.getRoom_id().equals(reservationDTO.))
//
//           ReservationDTO enoughSeatsCheck = reservationDTOList.stream()
//                                                    //table size            -    available seats on this table  = num of available size
//                   .filter(i->i.getNum_of_seats() <= tableDTO.getAvailable_seats().size())
////                           - tableDTO.getAvailable_seats()
////                           .stream().filter(j->j.equals(false)).count())
//                   // in this time
//                   .filter(x -> x.getTime().getHours() == reservationDTO.getTime().getHours())
//                   .findFirst().orElse(null);
//
//
//           // maxofSeats on this table - existedReservation.getNum_of_seats() < reservationDTO.getNum_of_seats()
//           if(existedReservation != null){
//               return new ReservationDTO("Sorry, this table is already reserved in this time");
//           }
//           if (enoughSeatsCheck != null){
//               return new ReservationDTO("Sorry, this table is only available for limited seats");
//           }
//       }
       ReservationDTO r = abstractRepository.reserveTable(reservationDTO,resource);

        Pusher pusher = new Pusher("1753712", "2d818fcce28a85b66b67", "54ca7ad665a3f290a976");
        pusher.setCluster("ap1");
        pusher.setEncrypted(true);
        pusher.trigger("admin-channel", "new-reservation", Collections.singletonMap("message", "new reservation added on a table"));
//        pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));

//        System.out.println("new reservation");
        return r;
//

    }
    public ReservationDTO update(Long id , ReservationDTO reservationDTO){
        Resource resource = resourceLoader.getResource("classpath:Reservations.json");
        return abstractRepository.UpdateById(id ,reservationDTO, resource);
    }

    public ReservationDTO getReservation(Long id){
        Resource resource = resourceLoader.getResource("classpath:Reservations.json");
        return abstractRepository.findDataById(id ,resource);
    }

    public String cancelReservation(Long id){
        Resource resource = resourceLoader.getResource("classpath:Reservations.json");

        abstractRepository.deleteById(id ,resource);
        return "Deleted Done successfully";
    }


}
