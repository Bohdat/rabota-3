package svetaylo_skriabin.laba3;

        import android.app.Activity;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.DragEvent;
        import android.view.View;

        import com.sdsmdg.harjot.vectormaster.VectorMasterView;
        import com.sdsmdg.harjot.vectormaster.models.PathModel;

        import java.util.Random;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VectorMasterView crossVector = findViewById(R.id.cross_vector);

        crossVector.setOnClickListener(new View.OnClickListener() {
            int ctr = 0;
            @Override
            public void onClick(View view) {
                Random random = new Random();
                PathModel cross = crossVector.getPathModelByName("cross" + ctr);
                cross.setFillColor(Color.rgb(
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)));
                crossVector.update();
                ctr++;
                ctr &= 3;
            }
        });

        crossVector.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                View.DragShadowBuilder crossShadow = new View.DragShadowBuilder(crossVector);
                view.startDrag(null, crossShadow, null, 0);
                return true;
            }
        });

        findViewById(R.id.layout).setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        return true;
                    case DragEvent.ACTION_DROP:
                        crossVector.setX(dragEvent.getX() - crossVector.getPivotX());
                        crossVector.setY(dragEvent.getY() - crossVector.getPivotY());
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;
                }
                return false;
            }
        });
    }
}