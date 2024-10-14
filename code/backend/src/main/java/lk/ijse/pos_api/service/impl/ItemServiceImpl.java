package lk.ijse.pos_api.service.impl;

import lk.ijse.pos_api.dto.ItemDTO;
import lk.ijse.pos_api.entity.Item;
import lk.ijse.pos_api.repo.ItemRepo;
import lk.ijse.pos_api.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemRepo itemRepo;

    public List<ItemDTO> getALl() {
        try {
            List<Item> all = itemRepo.findAll();
            if (all.isEmpty() || all == null) {
                return new ArrayList<>();
            } else {
                List<ItemDTO> list = modelMapper.map(all, new TypeToken<List<ItemDTO>>() {
                }.getType());
                List<ItemDTO> itemDTOS=new ArrayList<>();

                for (ItemDTO dto : list) {
                    dto.setImage(getImage(dto.getImage()));
                    itemDTOS.add(dto);

                }
                return itemDTOS;
            }
        } catch (Exception exception) {
            throw exception;
        }

    }

    public ItemDTO getItem(int id) {
        try {
            Optional<Item> byId = itemRepo.findById(id);
            if (byId.isPresent()) {
                ItemDTO itemDTO = modelMapper.map(byId.get(), ItemDTO.class);
                itemDTO.setImage(getImage(itemDTO.getImage()));
                return itemDTO;
            }
            return null;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public boolean saveItem(ItemDTO itemDTO) {
        try {
            String image = itemDTO.getImage();
            itemDTO.setImage("temp path /...");
            Item item = itemRepo.save(modelMapper.map(itemDTO, Item.class));

            String url = saveImage(image, "img" + item.getCode());
            itemDTO.setCode(item.getCode());
            itemDTO.setImage(url);

            Optional<Item> byId = itemRepo.findById(item.getCode());
            if (byId.isPresent()) {
                Item item_updated= itemRepo.save(modelMapper.map(itemDTO, Item.class));
                return item_updated != null;
            }
            return item != null;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }

    }

    public boolean updateItem(ItemDTO itemDTO) {
        try {
            Optional<Item> optionalItem = itemRepo.findById(itemDTO.getCode());
            if (optionalItem.isPresent()) {
                String image = itemDTO.getImage();
                itemDTO.setImage("temp path /...");
                Item item = itemRepo.save(modelMapper.map(itemDTO, Item.class));

                String url = saveImage(image, "img" + item.getCode());
                itemDTO.setImage(url);

                Optional<Item> byId = itemRepo.findById(item.getCode());
                if (byId.isPresent()) {
                    Item item_updated= itemRepo.save(modelMapper.map(itemDTO, Item.class));
                    return item_updated != null;
                }
                return item != null;
            }
            return false;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public boolean deleteItem(int id) {
        try {
            Optional<Item> byId = itemRepo.findById(id);
            if (byId.isPresent()) {
                itemRepo.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    private String saveImage(String base64Image, String name) {

        String fileExtension = getImageExtension(base64Image);
        if (fileExtension == null) {
            throw new IllegalArgumentException("Invalid image format");
        }
        String projectDir = System.getProperty("user.dir");
        String filePath = projectDir + "/src/img/" + name + fileExtension;

        byte[] imageBytes = Base64.getDecoder().decode(getBase64Data(base64Image));

        try {
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            Files.write(path, imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private String getImageExtension(String base64Image) {
        String regex = "data:image/(.*?);base64,";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(base64Image);

        if (matcher.find()) {
            String imageType = matcher.group(1);
            switch (imageType) {
                case "png":
                    return ".png";
                case "jpeg":
                case "jpg":
                    return ".jpg";
                case "gif":
                    return ".gif";
                // Add more cases as needed
                default:
                    return null; // Unsupported image type
            }
        }

        return null; // No match found
    }

    private String getBase64Data(String base64Image) {
        String regex = "base64,(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(base64Image);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return base64Image; // Return the original if no base64 data is found
    }

    public String getImage(String path) {
        try {

            File imageFile = new File(path);

            // Read the image bytes
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

            // Encode the image bytes to base64
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Return the base64-encoded image (you can add the data URL prefix if needed)
            return "data:image/png;base64," + base64Image;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to retrieve image!";
        }
    }

}
