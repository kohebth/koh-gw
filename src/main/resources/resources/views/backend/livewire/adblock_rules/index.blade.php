<div>
    <div class="row mt-4">
        <div class="col">
            <div class="form-group row">
                <div class="col-3">
                    {{ html()->label('Value')->class('form-control-label') }}
                    <div>
                        <input type="text" class="form-control my-2" wire:model.defer="filter.rule"/>
                    </div>
                </div>
                <div class="col-3">
                    {{html()->label('Type')->class('form-control-label')}}
                    <div class="pt-2">
                        <select wire:model.defer="filter.type" name="type" class="form-control">
                            <option value="">{{__("All")}}</option>
                            @foreach($types as $type)
                                <option value="{{$type}}">{{$type}}</option>
                            @endforeach
                        </select>
                    </div>
                </div>
                <div class="col-3">
                    {{html()->label('Status')->class('form-control-label')}}
                    <div class="pt-2">
                        <select wire:model.defer="filter.status" name="status" class="form-control">
                            <option value="">{{__("All")}}</option>
                            @foreach($status as $value)
                                <option value="{{$value}}">{{$value}}</option>
                            @endforeach
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <button type="button" title="Search" class='btn btn-info' action-search>
                    <i class="fas fa-search"></i> {{__("Search")}}
                </button>
                <x-buttons.clear> {{__('labels.buttons.general.clear')}}</x-buttons.clear>
            </div>
            <div class="form-horizontal content-loading" style="overflow: auto">
                <x-loading/>
                <table class="table table-bordered table-hover table-responsive-sm">
                    <thead>
                    <tr>
                        <th>Value</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Note</th>
                        <th>Created By</th>
                        <th>Created time</th>
                        <th>Updated By</th>
                        <th>Updated time</th>
                        <th width="200px">{{ __('labels.backend.action') }}</th>
                    </tr>
                    </thead>
                    <tbody>
                    @if ($data->count() > 0)
                        @foreach ($data as $item)
                            <tr wire:key="_id-{{ $item->id }}">
                                <td>{{$item->rule}}</td>
                                <td>{{$item->type}}</td>
                                <td>{{$item->status}}</td>
                                <td>{{$item->note}}</td>
                                <td>{{$item->created_by ? $item->createdBy->username : ''}}</td>
                                <td>{{$item->created_at}}</td>
                                <td>{{$item->updated_by ? $item->updatedBy->username : ''}}</td>
                                <td>{{$item->updated_at}}</td>
                                <td>
                                    @include('backend.includes.action_column.edit-action')
                                    @include('backend.includes.action_column.active-action')
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
