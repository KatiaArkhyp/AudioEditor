package org.example;

import org.example.converter.AudioMp3Converter;
import org.example.converter.AudioOggConverter;
import org.example.converter.AudioFlacConverter;
import org.example.audiotrack.Audiotrack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AudioEditorUI {
    private JFrame frame;

    public AudioEditorUI() {
        frame = new JFrame("Audio Editor");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton convertToMp3Button = new JButton("Convert to MP3");
        convertToMp3Button.setBounds(50, 50, 180, 30);
        frame.add(convertToMp3Button);

        JButton convertToOggButton = new JButton("Convert to OGG");
        convertToOggButton.setBounds(50, 100, 180, 30);
        frame.add(convertToOggButton);

        JButton convertToFlacButton = new JButton("Convert to FLAC");
        convertToFlacButton.setBounds(50, 150, 180, 30);
        frame.add(convertToFlacButton);

        convertToMp3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Audiotrack audiotrack = getDummyAudiotrack();
                    File convertedFile = AudioMp3Converter.getInstance().convertTo(audiotrack);
                    JOptionPane.showMessageDialog(frame, "Converted to MP3: " + convertedFile.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to convert to MP3: " + ex.getMessage());
                }
            }
        });

        convertToOggButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Audiotrack audiotrack = getDummyAudiotrack();
                    File convertedFile = AudioOggConverter.getInstance().convertTo(audiotrack);
                    JOptionPane.showMessageDialog(frame, "Converted to OGG: " + convertedFile.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to convert to OGG: " + ex.getMessage());
                }
            }
        });

        convertToFlacButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Audiotrack audiotrack = getDummyAudiotrack();
                    File convertedFile = AudioFlacConverter.getInstance().convertTo(audiotrack);
                    JOptionPane.showMessageDialog(frame, "Converted to FLAC: " + convertedFile.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to convert to FLAC: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }

    private Audiotrack getDummyAudiotrack() {
        return new Audiotrack() {
            @Override
            public it.sauronsoftware.jave.AudioAttributes getAudioAttributes() {
                it.sauronsoftware.jave.AudioAttributes attributes = new it.sauronsoftware.jave.AudioAttributes();
                attributes.setCodec("libmp3lame");
                attributes.setBitRate(128000);
                attributes.setChannels(2);
                attributes.setSamplingRate(44100);
                return attributes;
            }

            @Override
            public File getFileLink() {
                return new File("C:\\Users\\admin\\Desktop\\1sem\\AudioEditor\\testsongs\\mpthree.mp3");
            }

            @Override
            public javax.sound.sampled.AudioInputStream getAudioInputStream() {
                return null;
            }

            @Override
            public Audiotrack copy() {
                return this;
            }
        };
    }
}
