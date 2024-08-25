@if($message)
    <div class="alert alert-{{ $message['level'] }} {{ $message['important'] ? 'alert-important' : '' }}" role="alert">
        @if ($message['important'])
            <button type="button"
                    class="close"
                    data-dismiss="alert"
                    aria-hidden="true"
            >&times;</button>
        @endif

        {!! $message['message'] !!}
    </div>
@else
    <div></div>
@endif

@push('after-scripts')
    <script>
        $('div.alert').delay(2000).fadeOut(350);
    </script>
@endpush
