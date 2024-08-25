<div>
    <div class="row mt-4">
        <div class="col">
            <div class="form-group row">
                <div class="col-3">
                    {{ html()->label('Name')->class('form-control-label') }}
                    <div>
                        <input type="text" class="form-control my-2" wire:model.defer="filter.name"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <button type="button" title="Search" class='btn btn-info' action-search>
                    <i class="fas fa-search"></i> {{__("Search")}}
                </button>
                <x-buttons.clear> {{__('labels.buttons.general.clear')}}</x-buttons.clear>
            </div>
            <div class="form-horizontal content-loading">
                <x-loading/>
                <table class="table table-bordered table-hover table-responsive-sm">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Guard Name</th>
                        <th>Created time</th>
                        <th>Updated time</th>
                        <th width="150px">{{ __('labels.backend.action') }}</th>
                    </tr>
                    </thead>
                    <tbody>

                    @if ($data->count() > 0)
                        @foreach ($data as $item)
                            <tr wire:key="_id-{{ $item->id }}">
                                <td>{{$item->name}}</td>
                                <td>{{$item->guard_name}}</td>
                                <td>{{$item->created_at}}</td>
                                <td>{{$item->updated_at}}</td>
                                <td>
                                    @include('backend.includes.action_column.edit-action')
                                    @include('backend.includes.action_column.del-action')
                                </td>
                            </tr>
                        @endforeach

                    @else
                        <tr><td colspan="12">{{__("No record")}}</td></tr>
                    @endif
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-7">
            <div class="float-left">
                {!! $data->total() !!} {{ __('labels.backend.total') }}
            </div>
        </div>
        <div class="col-5">
            <div class="float-right">
                {!! $data->links() !!}
            </div>
        </div>
    </div>
</div>

@push('after-scripts')
    <script type="text/javascript">
        $("[action-search]").on('click', function (e) {
        @this.search();
        });
        $( 'body' ).on( 'click', '.js-btn-copy', function () {
            const id = $(this).attr('data-id');

            const copyContent = async () => {
                try {
                    const text = $('.js-btn-copy[data-id=' + id + ']').attr('data-content')
                    await navigator.clipboard.writeText(text);
                } catch (err) {
                    console.error('Failed to copy: ', err);
                }
            }
            setTimeout(copyContent, 1000);
        })
    </script>
@endpush
