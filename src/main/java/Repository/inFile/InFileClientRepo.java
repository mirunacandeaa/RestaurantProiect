//package Repository.inFile;
//
//import Model.Client;
//import Repository.IClientRepository;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
//import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//public class InFileClientRepo implements IClientRepository {
//
//    private ObjectMapper objectMapper;
//    public InFileClientRepo(String filename) {
//        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
//                .allowIfSubType("com.baeldung.jackson.inheritance")
//                .allowIfSubType("java.util.ArrayList")
//                .build();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
//        this.filename=filename;
//        try {
//            clientList = objectMapper.readValue(new File(filename), objectMapper.getTypeFactory().constructCollectionType(List.class, Client.class));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Client>clientList;
//    private String filename;
//
//    @Override
//    public boolean add(Client client) {
//        for(Client c : clientList)
//            if(c.equals(client))
//                return false;
//        clientList.add(client);
//        try {
//            new FileOutputStream(filename).close(); //sterge contentul din fisier
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            objectMapper.writeValue(new File(filename),clientList); //adauga arrayul in fisier
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean delete(Integer ID) {
//        for(Client c : clientList)
//            if(c.getClientID().equals(ID)){
//                clientList.remove(c);
//                try {
//                    new FileOutputStream(filename).close(); //sterge contentul din fisier
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                try {
//                    objectMapper.writeValue(new File(filename),clientList); //adauga arrayul in fisier
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                return true;
//            }
//        return false;
//    }
//
//    @Override
//    public boolean update(Integer ID, Client client) {
//        for(Client c : clientList){
//            if(c.getClientID().equals(ID)){
//                c=client;
//                try {
//                    new FileOutputStream(filename).close(); //sterge contentul din fisier
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                try {
//                    objectMapper.writeValue(new File(filename),clientList); //adauga arrayul in fisier
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public Client findbyId(Integer ID) {
//        for(Client c : clientList){
//            if(c.getClientID().equals(ID)){
//                return c;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<Client> getAll() {
//        return clientList;
//    }
//}
