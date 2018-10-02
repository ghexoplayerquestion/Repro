package com.example.ghexoplayerquestion.repro;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
                        getApplicationContext(),
                        new DefaultTrackSelector());

                String manifestString = "<MPD xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" profiles=\"urn:mpeg:dash:profile:isoff-main:2011\" mediaPresentationDuration=\"PT0M11.508982S\" minBufferTime=\"PT6S\" xmlns=\"urn:mpeg:dash:schema:mpd:2011\">\n" +
                        "  <Period start=\"PT0S\" duration=\"PT2.017435S\">\n" +
                        "    <AdaptationSet mimeType=\"audio/mp4\" codecs=\"mp4a.40.2\" contentType=\"audio\">\n" +
                        "      <Representation audioSamplingRate=\"44100\" id=\"1\" bandwidth=\"32000\">\n" +
                        "        <BaseURL>https://rhhhloggermediastore.blob.core.windows.net/rh-logger-share/1b4966c2-26f2-403a-8808-67b4d5488c8c.mp4</BaseURL>\n" +
                        "        <SegmentBase timescale=\"1000000\" presentationTimeOffset=\"21333\" />\n" +
                        "      </Representation>\n" +
                        "    </AdaptationSet>\n" +
                        "  </Period>\n" +
                        "  <Period duration=\"PT1.809909S\">\n" +
                        "    <AdaptationSet mimeType=\"audio/mp4\" codecs=\"mp4a.40.2\" contentType=\"audio\">\n" +
                        "      <Representation audioSamplingRate=\"44100\" id=\"1\" bandwidth=\"32000\">\n" +
                        "        <BaseURL>https://rhhhloggermediastore.blob.core.windows.net/rh-logger-share/f0d7b23b-878c-4be4-9832-6cf8539c6331.mp4</BaseURL>\n" +
                        "        <SegmentBase timescale=\"1000000\" presentationTimeOffset=\"21333\" />\n" +
                        "      </Representation>\n" +
                        "    </AdaptationSet>\n" +
                        "  </Period>\n" +
                        "  <Period duration=\"PT1.842343S\">\n" +
                        "    <AdaptationSet mimeType=\"audio/mp4\" codecs=\"mp4a.40.2\" contentType=\"audio\">\n" +
                        "      <Representation audioSamplingRate=\"44100\" id=\"1\" bandwidth=\"32000\">\n" +
                        "        <BaseURL>https://rhhhloggermediastore.blob.core.windows.net/rh-logger-share/a17539ae-8064-42a0-bd10-7d04cd2b2886.mp4</BaseURL>\n" +
                        "        <SegmentBase timescale=\"1000000\" presentationTimeOffset=\"21333\" />\n" +
                        "      </Representation>\n" +
                        "    </AdaptationSet>\n" +
                        "  </Period>\n" +
                        "  <Period duration=\"PT2.102876S\">\n" +
                        "    <AdaptationSet mimeType=\"audio/mp4\" codecs=\"mp4a.40.2\" contentType=\"audio\">\n" +
                        "      <Representation audioSamplingRate=\"44100\" id=\"1\" bandwidth=\"32000\">\n" +
                        "        <BaseURL>https://rhhhloggermediastore.blob.core.windows.net/rh-logger-share/dbb6292a-b0fd-4036-86fc-eb8f2f2bff6c.mp4</BaseURL>\n" +
                        "        <SegmentBase timescale=\"1000000\" presentationTimeOffset=\"21333\" />\n" +
                        "      </Representation>\n" +
                        "    </AdaptationSet>\n" +
                        "  </Period>\n" +
                        "  <Period duration=\"PT1.952418S\">\n" +
                        "    <AdaptationSet mimeType=\"audio/mp4\" codecs=\"mp4a.40.2\" contentType=\"audio\">\n" +
                        "      <Representation audioSamplingRate=\"44100\" id=\"1\" bandwidth=\"32000\">\n" +
                        "        <BaseURL>https://rhhhloggermediastore.blob.core.windows.net/rh-logger-share/21190726-7468-4c5d-893c-fe30f642788d.mp4</BaseURL>\n" +
                        "        <SegmentBase timescale=\"1000000\" presentationTimeOffset=\"21333\" />\n" +
                        "      </Representation>\n" +
                        "    </AdaptationSet>\n" +
                        "  </Period>\n" +
                        "  <Period duration=\"PT1.784001S\">\n" +
                        "    <AdaptationSet mimeType=\"audio/mp4\" codecs=\"mp4a.40.2\" contentType=\"audio\">\n" +
                        "      <Representation audioSamplingRate=\"44100\" id=\"1\" bandwidth=\"32000\">\n" +
                        "        <BaseURL>https://rhhhloggermediastore.blob.core.windows.net/rh-logger-share/c662bb42-97ac-4eb4-b699-52a97932fd38.mp4</BaseURL>\n" +
                        "        <SegmentBase timescale=\"1000000\" presentationTimeOffset=\"21333\" />\n" +
                        "      </Representation>\n" +
                        "    </AdaptationSet>\n" +
                        "  </Period>\n" +
                        "</MPD>";

                DashManifest manifest;
                try {
                    manifest = (new DashManifestParser()).parse(
                            Uri.parse("http://localhost"),
                            new ByteArrayInputStream(manifestString.getBytes(StandardCharsets.UTF_8)));
                }
                catch (IOException e)
                {
                    throw new RuntimeException();
                }

                player.prepare((new DashMediaSource.Factory(
                        new DefaultDashChunkSource.Factory(
                                new DefaultDataSourceFactory(getApplicationContext(),
                                        "ghexoplayerquestion")),
                        new DefaultDataSourceFactory(getApplicationContext(),
                                "ghexoplayerquestion")
                )).createMediaSource(manifest));

                player.setPlayWhenReady(true);
            }
        });
    }


}
