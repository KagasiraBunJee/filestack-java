package model.transform;

import com.google.gson.JsonObject;
import model.Client;
import model.FileLink;

import java.io.IOException;

/**
 * {@link Transform Transform} subclass for image transformations.
 */
public class ImageTransform extends Transform {

    public ImageTransform(Client client, String source) {
        super(client, source);
    }

    public ImageTransform(FileLink fileLink) {
        super(fileLink);
    }

    public JsonObject debug() throws IOException {
        String tasksString = getTasksString();

        if (apiKey != null)
            return processService.debugExternal(apiKey, tasksString, source).execute().body();
        else
            return processService.debug(tasksString, source).execute().body();
    }

    // TODO This is just for demonstration, it should be confirmed when real transforms are added
    public ImageTransform resize(Integer width, Integer height, String fit, String align) {
        TransformTask task = new TransformTask("resize");
        task.addOption("width", width);
        task.addOption("height", height);
        task.addOption("fit", fit);
        task.addOption("align", align);
        tasks.add(task);
        return this;
    }
}
