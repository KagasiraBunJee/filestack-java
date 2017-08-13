package com.filestack.model.transform.tasks.faces;

import static org.junit.Assert.assertTrue;

import com.filestack.model.transform.base.TransformTask;
import org.junit.Test;

public class TestPixelateFacesTask {

    @Test
    public void testToString() {
        String correct = "pixelate_faces=" 
                + "faces:1," 
                + "minsize:200," 
                + "maxsize:300," 
                + "buffer:50," 
                + "amount:5," 
                + "blur:10.0," 
                + "type:oval";

        TransformTask task = new PixelateFacesTask.Builder()
                .faces(1)
                .minSize(200)
                .maxSize(300)
                .buffer(50)
                .amount(5)
                .blur(10)
                .type("oval")
                .build();

        String output = task.toString();

        String message = String.format("Task string malformed\nCorrect: %s\nOutput: %s",
                correct, output);
        assertTrue(message, output.equals(correct));
    }

    @Test
    public void testToStringFacesArray() {
        String correct = "pixelate_faces="
                + "faces:[1,2,3,4],"
                + "minsize:200,"
                + "maxsize:300,"
                + "buffer:50,"
                + "amount:5,"
                + "blur:10.0,"
                + "type:oval";

        TransformTask task = new PixelateFacesTask.Builder()
                .faces(1, 2, 3, 4)
                .minSize(200)
                .maxSize(300)
                .buffer(50)
                .amount(5)
                .blur(10)
                .type("oval")
                .build();

        String output = task.toString();

        String message = String.format("Task string malformed\nCorrect: %s\nOutput: %s",
                correct, output);
        assertTrue(message, output.equals(correct));
    }

    @Test
    public void testToStringFacesAll() {
        String correct = "pixelate_faces="
                + "faces:all,"
                + "minsize:200,"
                + "maxsize:300,"
                + "buffer:50,"
                + "amount:5,"
                + "blur:10.0,"
                + "type:oval";

        TransformTask task = new PixelateFacesTask.Builder()
                .faces("all")
                .minSize(200)
                .maxSize(300)
                .buffer(50)
                .amount(5)
                .blur(10)
                .type("oval")
                .build();

        String output = task.toString();

        String message = String.format("Task string malformed\nCorrect: %s\nOutput: %s",
                correct, output);
        assertTrue(message, output.equals(correct));
    }

    @Test
    public void testToStringSizeFloats() {
        String correct = "pixelate_faces="
                + "faces:1,"
                + "minsize:0.35,"
                + "maxsize:0.55,"
                + "buffer:50,"
                + "amount:5,"
                + "blur:10.0,"
                + "type:oval";

        TransformTask task = new PixelateFacesTask.Builder()
                .faces(1)
                .minSize(.35)
                .maxSize(.55)
                .buffer(50)
                .amount(5)
                .blur(10)
                .type("oval")
                .build();

        String output = task.toString();

        String message = String.format("Task string malformed\nCorrect: %s\nOutput: %s",
                correct, output);
        assertTrue(message, output.equals(correct));
    }
}
